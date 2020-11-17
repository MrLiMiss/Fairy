package com.tengfei.fairy.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.RandomAccessFile;


/**
 *
 * 文件工具类
 * @param <T>
 */
public class FileUtil<T> {

    private static FileUtil fileUtil;

    public static synchronized FileUtil getInstance(){
        if(null ==fileUtil){
            fileUtil = new FileUtil();
        }
        return fileUtil;
    }


    public static final String SDCARD = Environment
            .getExternalStorageDirectory() + "/";


    /**
     * @param strcontent 字符串
     * @param filePath  文件路径
     * @param fileName  文件名称
     *                  将字符串写入相应文件
     */
    public static void writeTxtToFile(String strcontent, String filePath, String fileName) {
        getFilePath(filePath, fileName);
        String strFilePath = filePath + fileName;
        String strContent = strcontent + "\r\n";

        try {
            File file = new File(strFilePath);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }

            RandomAccessFile raf = new RandomAccessFile(file, "rwd");
            raf.seek(file.length());
            raf.write(strContent.getBytes());
            raf.close();
        } catch (Exception var7) {
        }

    }

    /**
     * @param filePath 文件目录
     * @param fileName 文件名称
     * @return   通过文件目录 文件名称获取的文件
     */
    public static File getFilePath(String filePath, String fileName) {
        File file = null;
        makeRootDirectory(filePath);

        try {
            file = new File(filePath + fileName);
        } catch (Exception var4) {
            var4.printStackTrace();
        }

        return file;
    }

    /**
     * @param filePath 文件夹目录
     *                 建立文件夹目录
     */
    public static void makeRootDirectory(String filePath) {
        File file = null;

        try {
            file = new File(filePath);
            if (!file.exists()) {
                file.mkdir();
            }
        } catch (Exception var3) {
        }

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
    public File creatSDDir(String dirName) {
        File dir = new File(SDCARD + dirName);
        dir.mkdir();
        return dir;
    }

    /**
     * 判断SD卡上的文件夹是否存在
     */
    public boolean isFileExist(String fileName) {
        File file = new File(SDCARD + fileName);
        return file.exists();
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
     * @Author zhaoqianpeng(zqp@yitong.com.cn) 2014-4-15
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
     * @param path
     * @return
     * @Description 文件重命名
     * @Author zhaoqianpeng(zqp@yitong.com.cn) 2014-4-15
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
     * @Author zhaoqianpeng(zqp@yitong.com.cn) 2014-4-15
     */
    public static void deleteFile(String path) {
        File file = new File(path);
        // 判断文件是否存在
        if (file.exists()) {
            // 判断是否是文件
            if (file.isFile()) {
                //删除
                file.delete();
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

    public  T getJsonFromAssets(Context context, Class<T> classOfT, String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            AssetManager assetManager = context.getAssets();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(assetManager.open(fileName)));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            Gson gson = new Gson();
            T result = gson.fromJson(stringBuilder.toString(), classOfT);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     *加载文件中的字符串
     * @param filePath
     * @param filename
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static String loadData(String filePath, String filename) throws FileNotFoundException, IOException {
        String reData = null;
        BufferedReader reader = null;
        StringBuilder data = new StringBuilder();

        try {
            File sdCard = new File(filePath, filename);
            FileInputStream in = new FileInputStream(sdCard);
            reader = new BufferedReader(new InputStreamReader(in));
            new String();

            String line;
            while((line = reader.readLine()) != null) {
                data.append(line);
            }

            reData = data.toString();
        } catch (FileNotFoundException var11) {
            reData = null;
        } finally {
            reader.close();
        }

        return reData;
    }

    /**
     * 文件是否存在
     * @param path
     * @return
     */
    public static boolean Isfiles(String path) {
        try {
            File f = new File(path);
            return f.exists();
        } catch (Exception var2) {
            return false;
        }
    }

}
