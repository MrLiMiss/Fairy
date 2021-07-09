package com.tengfei.fairy.utils;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;

import androidx.core.content.FileProvider;

import com.tengfei.fairy.config.FileConfig;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static android.content.Context.MODE_PRIVATE;

/**
 * android 文件处理utils
 */
public class FileUtil {
    public static  String TAG=FileUtil.class.getSimpleName();

    public static final String SDCARD = FileConfig.getInstance().getDownloadFilePath() + "/";

    //图库绝对地址
    public static final String SDCARD_CHILD_GALLERY = File.separator + "Fairy";
    public static final String SDCARD_GALLERY = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getPath() + SDCARD_CHILD_GALLERY;
    public static final String SDCARD_GALLERY_Q = Environment.DIRECTORY_PICTURES + SDCARD_CHILD_GALLERY;



    /**
     * 概念区分：
     * （1）内部存储、外部存储
     * 4.4（Build.VERSION_CODES.KITKAT）版本以前的手机，手机内部存储就是手机的rom，手机外部存储就是外插SD卡的存储
     * 4.4版本手机以后，内部存储为手机系统的一个特殊位置，文件存储在内部存储，文件默认只能被你的app访问。
     * 4.4以上手机ROM 分为内部存储internal  +  外部存储external。外置SD也属于外部存储。
     *
     * 应用在被用户卸载后，SDCard/Android/data/你的应用的包名/ 这个目录下的所有文件都会被删除
     *
     * /storage/emulated/0 对应手机内部SD卡目录   /storage/emulated/1 对应手机外部SD卡目录
     *
     * 1、Context.getExternalFilesDir()方法可以获取到 SDCard/Android/data/你的应用的包名/files/ 目录，
     * 一般放一些长时间保存的数据，app删除数据删除，设置->应用->应用详情里面的”清除数据“
     * 2、Context.getExternalCacheDir()方法可以获取到 SDCard/Android/data/你的应用包名/cache/目录，
     * 一般存放临时缓存数据，app删除数据删除，设置->应用->应用详情里面的”清除缓存“，此方法需要判断外部SD卡状态是否可用
     *
     * 3、getCacheDir()方法用于获取/data/data/<application package>/cache目录
     * 4、getFilesDir()方法用于获取/data/data/<application package>/files目录
     */

    public static void test(Context context){

        try {
            //访问内部存储空间
            Log.d(TAG,"内部存储根路径 Environment.getDataDirectory(): "+Environment.getDataDirectory());
            Log.d(TAG,"内部存储Cache路径 getCacheDir: "+context.getCacheDir().getAbsolutePath());
            Log.d(TAG,"内部存储files路径 getFilesDir: "+context.getFilesDir().getAbsolutePath());
            Log.d(TAG,"Environment.getDataDirectory():"+Environment.getDataDirectory());
            Log.d(TAG,"内部存储自定义路径（myFile） context.getDir(\"myFile\", MODE_PRIVATE):"+context.getDir("myFile", MODE_PRIVATE).getAbsolutePath());

            //访问外部存储空间
            Log.d(TAG,"外部存储根路径 Environment.getExternalStorageDirectory():"+Environment.getExternalStorageDirectory());
            //  getExternalCacheDir  getExternalFilesDir  4.4 以下版本返回null
            Log.d(TAG,"外部存储files路径 getExternalCacheDir: "+context.getExternalCacheDir().getAbsolutePath());
            Log.d(TAG,"外部存储Cache路径 getExternalFilesDir: "+ context.getExternalFilesDir(null).getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 遍历外部存储控件
     * @param context
     */
    public static void testExternal(Context context){
        File[] files;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            files = context.getExternalFilesDirs(Environment.MEDIA_MOUNTED);
            for(File file:files){
                Log.e(TAG,"外部存储空间："+file.getAbsolutePath());
            }
        }

    }


    /**
     * 测试沙箱存储机制
     */
    public  static void testSandbox(){

    }


    /**
     * 获取app缓存路径
     * getExternalCacheDir() 需外部SD卡状态可用
     * @param context
     * @return
     */
    public static String getCachePath( Context context ){
        String cachePath ;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            //外部存储可用
            cachePath = context.getExternalCacheDir().getPath() ;
            Logs.d(TAG,"外部存储可用:"+cachePath);
        }else {
            //外部存储不可用
            cachePath = context.getCacheDir().getPath() ;
            Logs.d(TAG,"外部存储不可用"+cachePath);
        }
        return cachePath ;
    }

    /**
     * 在SD卡上创建文件
     *
     * @throws IOException
     */
    public File creatSDFile(String fileName) throws IOException {
        File file = new File(SDCARD + fileName);
        file.createNewFile();
        return file;
    }

    /**
     * 在SD卡上创建目录
     *
     * @param dirName
     */
    public static File creatSDDir(String dirName) {
        File dir = new File(dirName);
        dir.mkdir();
        return dir;
    }

    /**
     * 判断SD卡上的文件夹是否存在
     */
    public static boolean isFileExist(String filePath) {
        File file = new File(filePath);
        return file.exists();
    }


    /**
     * base64编码转化成pdf文件
     */

    public static void base64StringToPdf(String base64Content, String filePath) {
        BufferedInputStream bis = null;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;

        try {
            byte[] bytes = Base64.decode(base64Content, Base64.DEFAULT);//base64编码内容转换为字节数组
            ByteArrayInputStream byteInputStream = new ByteArrayInputStream(bytes);
            bis = new BufferedInputStream(byteInputStream);
            File file = new File(filePath);
            File path = file.getParentFile();
            if (!path.exists()) {
                path.mkdirs();
            }
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);

            byte[] buffer = new byte[1024];
            int length = bis.read(buffer);
            while (length != -1) {
                bos.write(buffer, 0, length);
                length = bis.read(buffer);
            }
            bos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bis != null) {
                    bis.close();
                }
                if (fos != null) {
                    fos.close();
                }
                if (bos != null) {
                    bos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 将一个InputStream里面的数据写入到SD卡中
     */
    public File write2SDFromInput(String path, String fileName,
                                  InputStream input) {
        File file = null;
        OutputStream output = null;
        try {
            creatSDDir(path);
            file = creatSDFile(path + fileName);
            output = new FileOutputStream(file);
            byte buffer[] = new byte[4 * 1024];
            while ((input.read(buffer)) != -1) {
                output.write(buffer);
            }
            output.flush();
        } catch (Exception e) {
            Logs.d("TAG", e.toString());
        } finally {
            try {
                output.close();
            } catch (Exception e) {
                Logs.d("TAG", e.toString());
            }
        }
        return file;
    }

    /**
     * @param path
     * @return
     * @Description 判断文件是否存在
     * @Author zhaoqianpeng(zqp @ yitong.com.cn) 2014-4-15
     */
    public static Boolean exist(String path) {
        File file = new File(path);
        Boolean exist = false;
        try {
            exist = file.exists();
            file = null;
        } catch (Exception e) {
            Log.w("FileUtil", "file exists(" + path + ") error!");
        }
        return exist;
    }

    /**
     * 创建文件
     *
     * @param path 文件路径+文件名
     * @param
     * @return
     */
    public static boolean createFile(String path) {
        File file = new File(path);
        Boolean createFlg = false;
        // 按照指定的路径创建文件夹
        if (!file.exists()) {
            file.mkdirs();
        }
        String local_file = file.getAbsolutePath();
        file = new File(local_file);
        if (!file.exists()) {
            try {
                // 创建新文件
                createFlg = file.createNewFile();
            } catch (Exception e) {
                Logs.d("FileUtil", e.getMessage());
            }
        }
        return createFlg;
    }

    /**
     * 创建文件
     *
     * @param path     文件路径
     * @param filename 文件名
     * @return
     */
    public static boolean createFile(String path, String filename) {
        File file = new File(path);
        Boolean createFlg = false;
        // 按照指定的路径创建文件夹
        if (!file.exists()) {
            file.mkdirs();
        }
        String local_file = file.getAbsolutePath() + "/" + filename;
        file = new File(local_file);
        if (!file.exists()) {
            try {
                // 创建新文件
                createFlg = file.createNewFile();
            } catch (Exception e) {
                Logs.d("FileUtil", e.getMessage());
            }
        }
        return createFlg;
    }

    /**
     * @param
     * @return
     * @Description 文件重命名
     * @Author zhaoqianpeng(zqp @ yitong.com.cn) 2014-4-15
     */
    public static boolean renameTo(String oldPath, String newPath) {
        boolean renameFlg = false;
        File ofile = new File(oldPath);
        if (ofile.exists()) {
            File nfile = new File(newPath);
            renameFlg = ofile.renameTo(nfile);
        }
        return renameFlg;
    }

    /**
     * @param path
     * @Description 删除指定路径文件
     * @Author zhaoqianpeng(zqp @ yitong.com.cn) 2014-4-15
     */
    public static boolean deleteFile(String path) {
        File file = new File(path);
        // 判断文件是否存在
        if (file.exists()) {
            // 判断是否是文件
            if (file.isFile()) {
                //删除
                return file.delete();
            }
        }
        return false;
    }

    // 删除某个文件夹下的所有文件夹和文件
    public static void DeleteFile(File file) {
        if (!file.exists()) {
            Logs.d("TAG", "文件或文件夹不存在-------------------->");
            return;
        } else {
            if (file.isFile()) { //是文件，直接删除
                file.delete();
                Logs.d("TAG", "文件删除成功-------------------->");
                return;
            }
            if (file.isDirectory()) { //是目录
                File[] childFiles = file.listFiles();
                if (childFiles == null || childFiles.length == 0) { //空目录，直接删除
                    file.delete();
                    return;
                }
                for (File f : childFiles) {
                    DeleteFile(f);
                }
                file.delete();//删除最终空目录
            }
        }
    }

    public static void cache(String path, byte[] data) throws IOException {
        OutputStream os = null;
        try {
            os = new FileOutputStream(path);
            os.write(data);
        } catch (IOException e) {
            Log.w("FileUtil", "file cache(" + path + ") error!");
        } finally {
            if (null != os)
                os.close();
            os = null;
        }
    }

    public static boolean checkIsImgFile(String path) {
        boolean isImgFile = false;
        // 获取扩展名
        String fileEnd = path.substring(path.lastIndexOf(".") + 1,
                path.length()).toLowerCase();
        if (fileEnd.equals("png") || fileEnd.equals("jpg")) {
            isImgFile = true;
        } else {
            isImgFile = false;
        }
        return isImgFile;
    }

    public static boolean checkIsPdfFile(String path) {
        boolean isPdfFile = false;
        // 获取扩展名
        String fileEnd = path.substring(path.lastIndexOf(".") + 1,
                path.length()).toLowerCase();
        if (fileEnd.equals("pdf")) {
            isPdfFile = true;
        } else {
            isPdfFile = false;
        }
        return isPdfFile;
    }

    public static boolean checkIsVideoFile(String path) {
        boolean isVideoFile = false;
        // 获取扩展名
        String fileEnd = path.substring(path.lastIndexOf(".") + 1,
                path.length()).toLowerCase();
        if (fileEnd.equals("mp4") || fileEnd.equals("3gp")) {
            isVideoFile = true;
        } else {
            isVideoFile = false;
        }
        return isVideoFile;
    }

    public static String getFileName(String path) {
        // String fileEnd = path.substring(path.lastIndexOf("/") + 1,
        // path.length());
        String fileName = path.substring(path.lastIndexOf("/")).substring(1);
        return fileName;
    }

    public static Bitmap getDiskBitmap(String pathString) {
        Bitmap bitmap = null;
        try {
            File file = new File(pathString);
            if (file.exists()) {
                bitmap = BitmapFactory.decodeFile(pathString);
            }
        } catch (Exception e) {
        }
        return bitmap;
    }

    /**
     * bitmap转为base64
     *
     * @param bitmap
     * @return
     */
    public static String bitmapToBase64(Bitmap bitmap) {
        String result = null;
        ByteArrayOutputStream baos = null;
        if (bitmap != null) {
            baos = new ByteArrayOutputStream();
            bitmap.compress(CompressFormat.PNG, 100, baos);
            byte[] bitmapBytes = baos.toByteArray();
            result = Base64.encodeToString(bitmapBytes, Base64.DEFAULT);
            try {
                if (baos != null) {
                    baos.flush();
                    baos.close();
                }
            } catch (IOException e) {
                Logs.d("TAG", e.toString());
            }
        }
        return result;
    }

    /*
     * 为文件名重定义类型
     * path 文件路径名
     * type 重新定义的类型字符串
     */
    public static String reTypeName(String path, String type) {
        String retypename = getFileName(path).substring(0, getFileName(path).lastIndexOf(".")) + type;
        ;
        return retypename;
    }

    // 把drawable的图片保存到本地
    public static boolean saveBitmap2file(Activity activity, int rId, String filename) {
        Bitmap bitmap = BitmapFactory.decodeResource(activity.getResources(),
                rId);
        CompressFormat format = CompressFormat.JPEG;
        int quality = 100;
        OutputStream stream = null;
        try {
            stream = new FileOutputStream(FileConfig.getInstance().getImgPath() + filename);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
        }
        return bitmap.compress(format, quality, stream);
    }

    // 判断设备版本
    public static boolean isVersion(String str) {
        boolean isVersion = true;
        if (Float.parseFloat(str.substring(0, 3)) < 4.0) {
            isVersion = false;
        } else {
            isVersion = true;
        }
        return isVersion;
    }

    public static byte[] readInputStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outSteam.write(buffer, 0, len);
        }
        outSteam.close();
        inStream.close();
        return outSteam.toByteArray();
    }

    public static Object loadFromFile(Context context, String path, String name) {

//        Object obj=null;
//        File file = new File(path + File.separator +  name);
//        try {
//
//            if (file.exists()) {
//                FileInputStream fis = new FileInputStream(file);
////                ObjectInputStream ois = new ObjectInputStream(fis);
//                try {
//                    obj = fis.read();
//                } catch (IOException e) {
//                    Logs.e("yanxm"," read file e="+e);
//                }
//                fis.close();
//            }
//        } catch (IOException e) {
//            Logs.e("yanxm"," loadFromFile e="+e);
//        }
//        return obj;

        String str = "";
        try {
            File readFile = new File(path + File.separator + name);
            if (!readFile.exists()) {
                return null;
            }
            FileInputStream inStream = new FileInputStream(readFile);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            byte[] buffer = new byte[2048];
            int length = -1;
            while ((length = inStream.read(buffer)) != -1) {
                stream.write(buffer, 0, length);
            }
            str = stream.toString();
            stream.close();
            inStream.close();
            return str;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String HISTOEY_PATH = "HRBank/history";

    public static void saveToFile(Context context, String obj, String path, String name) {
        String mFilePath;
        File file;
        try {
            if (!isFolderExists(path)) {
                createFile(path, name);
            }
            file = new File(path + File.separator + name);
            if (!isFileExist(path + File.separator + name)) {
                file.createNewFile();
            } else {
                obj = "," + obj;
            }

            FileOutputStream fos = new FileOutputStream(file, true);
            fos.write(obj.getBytes());
            fos.flush();
            fos.close();
        } catch (IOException e) {
            Logs.e("yanxm", "  save  fail e=" + e);

        }
    }

    public static void saveListToFile(Context context, String obj, String path, String name) {
        String mFilePath;
        File file;
        try {
            if (!isFolderExists(path)) {
                createFile(path, name);
            }
            file = new File(path + File.separator + name);
            if (!isFileExist(path + File.separator + name)) {
                file.createNewFile();
            }

            FileOutputStream fos = new FileOutputStream(file, false);
            fos.write(obj.getBytes());
            fos.flush();
            fos.close();
        } catch (IOException e) {
            Logs.e("yanxm", "  save  fail e=" + e);

        }
    }

    public static boolean isFolderExists(String path) {
        File file = new File(path);
        if (!file.exists()) {
            if (file.mkdirs()) {
                return true;
            } else {
                return false;

            }
        }
        return true;

    }

    /**
     * 返回应用文件目录
     *
     * @return
     */
    public static File getFileDir(Context mContext) {
        return mContext.getFilesDir();
    }

    /**
     * 判断当前路径文件或文件夹是否存在
     *
     * @param filePath
     * @return
     */
    public static boolean isFileExits(String filePath) {
        File file = new File(filePath);
        return file == null ? false : file.exists();
    }

//    public File getCacheFile2(String id) {
//        DataCacheKeyVo dataCacheKey = new DataCacheKeyVo(new GlideUrl(id), EmptySignature.obtain());
//        SafeKeyGenerator safeKeyGenerator = new SafeKeyGenerator();
//        String safeKey = safeKeyGenerator.getSafeKey(dataCacheKey);
//        try {
//            int cacheSize = 100 * 1000 * 1000;
//            DiskLruCache diskLruCache = DiskLruCache.open(new File(getCacheDir(), DiskCache.Factory.DEFAULT_DISK_CACHE_DIR), 1, 1, cacheSize);
//            DiskLruCache.Value value = diskLruCache.get(safeKey);
//            if (value != null) {
//                return value.getFile(0);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    /**
     * 保存Bitmap到本地
     *
     * @param bitmap
     * @param path
     * @return
     */
    public static boolean saveBitmap(Bitmap bitmap, String path) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(CompressFormat.PNG, 100, bos);
        byte[] buffer = bos.toByteArray();
        if (buffer != null) {
            File file = new File(path);
            if (file.exists()) {
                file.delete();
            }
            OutputStream outputStream = null;
            try {
                outputStream = new FileOutputStream(file);
                outputStream.write(buffer);
                outputStream.close();
                return true;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /*public static void openPdf(Context context,String path) {
        try {
            File file = new File(path);
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.addCategory("android.intent.category.DEFAULT");
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            Uri uri;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                uri = FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".fileProvidor", file);
            } else {
                //            uri = Uri.fromFile(file);
                uri = Uri.parse("file://" + path);
            }
            intent.setDataAndType(uri, "application/pdf");
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "请安装WPS后打开",Toast.LENGTH_SHORT).show();
        }
    }*/


    /**
     * 文件存储路径
     * 目录：Android/data/包名/apk文件夹下
     *
     * @param mContext
     * @param packageName
     * @return
     */
    public static String getPath(Context mContext, String packageName) {
        File dir = mContext.getExternalFilesDir(null);
        if (dir == null) {
            return "";
        }

        File file = new File(dir.getAbsolutePath() + "/" + packageName);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file.getAbsolutePath();
    }


    /**
     * 保存图片到相册
     *
     * @param bitmap
     * @param fileName
     * @param context
     * @return
     */
    public static boolean saveBitmapToLocalGallery(Bitmap bitmap, String fileName, Context context) {
        if (bitmap == null)
            return false;
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.P) {
            File file = new File(SDCARD_GALLERY_Q);
            if (!file.exists()) {
                file.mkdirs();
            }
            ContentResolver contentResolver = context.getApplicationContext().getContentResolver();
            ContentValues contentValues = new ContentValues();
            contentValues.put(MediaStore.MediaColumns.RELATIVE_PATH, SDCARD_GALLERY_Q);
            contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, fileName);
            contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg");
            Uri uri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
            if (uri != null) {
                try {
                    OutputStream outputStream = contentResolver.openOutputStream(uri);
                    bitmap.compress(CompressFormat.JPEG, 100, outputStream);
                    notifyScanGallery(new File(SDCARD_GALLERY_Q, fileName + ".jpeg"), context);
                    return true;
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } else {
            File file = new File(SDCARD_GALLERY);
           FileUtil.createFile(file.getAbsolutePath());
            File filePic = new File(file, fileName + ".jpg");
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(filePic);
                bitmap.compress(CompressFormat.JPEG, 100, fileOutputStream);
                fileOutputStream.flush();
                fileOutputStream.close();
                notifyScanGallery(filePic, context);
                return true;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return false;
    }

    /**
     * 通知扫描图库
     */
    private static void notifyScanGallery(File filePic, Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            scanLocalFiles(filePic, context);
        } else {
            Uri uri;
            //适配7.0
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                uri = FileProvider.getUriForFile(context,
                        "com.yitong.hrb.people.android.fileProvidor",
                        filePic);
            } else {
                uri = Uri.fromFile(filePic);
            }
            if (StringUtil.isNotEmpty(uri.toString())) {
                context.getApplicationContext().sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri));
            }
        }
    }

    /**
     * 扫描SD卡
     *
     * @param filePath
     * @param context
     */
    private static void scanLocalFiles(File filePath, Context context) {
        String[] paths = new String[]{filePath.toString()};
        MediaScannerConnection.scanFile(context, paths, null, new MediaScannerConnection.OnScanCompletedListener() {
            @Override
            public void onScanCompleted(String path, Uri uri) {
                ContentResolver contentResolver = context.getApplicationContext().getContentResolver();
                long dateModified = 0;
                long dateadded = 0;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    Cursor cursor = contentResolver.query(uri, null, null, null);
                    if (cursor != null && cursor.moveToFirst()) {
                        dateModified = cursor.getLong(cursor.getColumnIndex(MediaStore.MediaColumns.DATE_MODIFIED));
                        dateadded = cursor.getLong(cursor.getColumnIndex(MediaStore.MediaColumns.DATE_ADDED));
                        cursor.close();
                    }
                    ContentValues contentValues = new ContentValues();
                    if (dateModified > 0) {
                        contentValues.put(MediaStore.MediaColumns.DATE_MODIFIED, dateModified / 1000);
                    }
                    if (dateadded > 0) {
                        contentValues.put(MediaStore.MediaColumns.DATE_ADDED, dateadded / 1000);
                    }
                    if (contentValues.size() > 0)
                        contentResolver.update(uri, contentValues, null, null);
                }
            }
        });
    }
}
