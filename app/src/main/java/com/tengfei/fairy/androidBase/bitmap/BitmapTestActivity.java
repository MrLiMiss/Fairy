package com.tengfei.fairy.androidBase.bitmap;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.tengfei.fairy.R;
import com.tengfei.fairy.activity.MainActivity;
import com.tengfei.fairy.mvp.BaseMvpActivity;
import com.tengfei.fairy.mvp.BasePresenter;
import com.tengfei.fairy.utils.Logs;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ Description :bitmap 相关测试
 *
 * 1、 BitmapDrawable：通用的图形对象，用于装载常用格式的图像，既可以是PNG，JPG这样的图像， 也是前面学的那13种Drawable类型的可视化对象！我们可以理解成一个用来放画的——画框！
 * 2、 Bitmap(位图)：我们可以把他看作一个画架，我们先把画放到上面，然后我们可以 进行一些处理，比如获取图像文件信息，做旋转切割，放大缩小等操作！
 * 3、 Canvas(画布)：如其名，画布，我们可以在上面作画(绘制)，你既可以用Paint(画笔)， 来画各种形状或者写字，又可以用Path(路径)来绘制多个点，然后连接成各种图形！
 * 4、 Matrix(矩阵)：用于图形特效处理的，颜色矩阵(ColorMatrix)，还有使用Matrix进行图像的 平移，缩放，旋转，倾斜等！
 *
 *
 * @ Author 李腾飞
 * @ Time 12/20/21   1:45 PM
 * @ Version :
 */
public class BitmapTestActivity extends BaseMvpActivity<BasePresenter> {

   public static String TAG=BitmapTestActivity.class.getSimpleName();

    @BindView(R.id.img_bitmap)
    ImageView imageView;
    @BindView(R.id.img_bitmap2)
    ImageView imageView2;//转换后的效果展示
    @BindView(R.id.img_bitmap3)
    ImageView imageView3;//转换后的效果展示

    @BindView(R.id.img_enlarge_after)
    ImageView mEnlargeAfter;

    @BindView(R.id.img_narrow_after)
    ImageView mNarrowAfter;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_bitmap;
    }



    @OnClick({R.id.img_bitmap,R.id.tv_bitmap,R.id.tv_rotate,R.id.tv_enlarge,R.id.tv_narrow,R.id.tv_cutting_des,R.id.tv_capture_screen})
    void onClick(View view){
        switch (view.getId()){
            case R.id.tv_bitmap:
                detailBitmap();
                break;
            case R.id.tv_rotate:
                bitmaoRotate();
                break;
            case R.id.tv_enlarge:
                bitmapEnlarge();
                break;
            case R.id.tv_narrow:
                bitmapNarrow();
                break;
            case R.id.tv_cutting_des://裁剪
                bitmapCutting();
                break;
            case R.id.tv_capture_screen://截屏
                captureScreen();
                break;
            default:
                break;

        }
    }

    /**
     * 截屏
     */
    private void captureScreen() {
        ByteArrayOutputStream byteOut = null;
        Runnable action = new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = null;
                View contentView = getWindow().getDecorView();
                try{
                    Log.e("HEHE",contentView.getHeight()+":"+contentView.getWidth());
                    bitmap = Bitmap.createBitmap(contentView.getWidth(),
                            contentView.getHeight(), Bitmap.Config.ARGB_4444);
                    contentView.draw(new Canvas(bitmap));
                    ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteOut);
                    savePic(bitmap, "sdcard/short.png");
                }catch (Exception e){e.printStackTrace();}
                finally {
                    try{
                        if (null != byteOut)
                            byteOut.close();
                        if (null != bitmap && !bitmap.isRecycled()) {
//                            bitmap.recycle();
                            bitmap = null;
                        }
                    }catch (IOException e){e.printStackTrace();}

                }
            }
        };
        try {
            action.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void savePic(Bitmap b, String strFileName) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(strFileName);
            if (null != fos) {
                boolean success= b.compress(Bitmap.CompressFormat.PNG, 100, fos);
                fos.flush();
                fos.close();
                if(success)
                    Toast.makeText(BitmapTestActivity.this, "截屏成功", Toast.LENGTH_SHORT).show();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * bitmap 裁剪
     */
    private void bitmapCutting() {
        Bitmap bitmap=BitmapFactory.decodeResource(getResources(),R.drawable.img_cat);
        Logs.e(TAG+"width:",bitmap.getWidth()+"");
        Logs.e(TAG+"height:",bitmap.getHeight()+"");
        Bitmap bitmap2=Bitmap.createBitmap(bitmap,0,300,438,550);
        Bitmap bitmap3=Bitmap.createBitmap(bitmap,0,500,438,352);
        imageView2.setImageBitmap(bitmap2);
        imageView3.setImageBitmap(bitmap3);
    }

    /**
     * bitmap 缩小
     *
     * 1、options.inJustDecodeBounds = false/true;
     * Options.inJustDecodeBounds=true,这时候decode的bitmap为null,只是把图片的宽高放在Options里，然后第二步就是设置合适的压缩比例inSampleSize
     * 2、options.inSampleSize = ssize;//图片压缩比例.
     */
    private void bitmapNarrow() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        // 通过这个bitmap获取图片的宽和高&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.image_injustdecodebounds, options);
        if (bitmap == null)
        {
            System.out.println("bitmap为空");
        }
        float realWidth = options.outWidth;
        float realHeight = options.outHeight;
        System.out.println("真实图片高度：" + realHeight + "宽度:" + realWidth);
        // 计算缩放比&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
        int scale = (int) ((realHeight > realWidth ? realHeight : realWidth) / 100);
        if (scale <= 0)
        {
            scale = 1;
        }
        options.inSampleSize = scale;
        options.inJustDecodeBounds = false;
        // 注意这次要把options.inJustDecodeBounds 设为 false,这次图片是要读取出来的。&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
        bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.image_injustdecodebounds, options);
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        System.out.println("缩略图高度：" + h + "宽度:" + w);
        mNarrowAfter.setImageBitmap(bitmap);
    }

    /**
     * bitmap 放大
     */
    private void bitmapEnlarge() {
        Bitmap bitmap =BitmapFactory.decodeResource(getResources(),R.drawable.image_flower);
        Logs.e(TAG+"width:",bitmap.getWidth()+"");
        Logs.e(TAG+"height:",bitmap.getHeight()+"");
        mEnlargeAfter.setImageBitmap(bitmap.createScaledBitmap(bitmap,840,717,true));

    }

    /**
     * bitmap 旋转
     */
    private void bitmaoRotate() {
    }


    /**
     * 加载 资源
     * 1、BitmapFactory.decodeResource()  加载drable
     * 2、BitmapFactory.decodeFile()  加载文件
     * 3、BitmapFactory.decodeStream() 加载流
     * 4、BitmapFactory.decodeByteArray() 加载字节数组
     *
     *
     * android中的大图片一般都要经过压缩才显示，不然容易发生oom，一般我们压缩的时候都只关注其尺寸方面的大小，其实除了尺寸之外，影响一个图片占用空间的还有其色彩细节。
     * 色彩存储方式：
     * Bitmap.Config.ALPHA_8    代表8位Alpha位图,每个像素占1字节
     * Bitmap.Config.ARGB_4444  代表16位ARGB位图，每个像素占2字节
     * Bitmap.Config.ARGB_8888  代表32位ARGB位图，每个像素占4字节
     * Bitmap.Config.RGB_565    代表8位RGB位图，每个像素占1字节
     */
    private void detailBitmap() {
        BitmapFactory.Options options=new BitmapFactory.Options();
//        options.inScaled=false;//不进行缩放操作。图片放在不同的项目目录下，会进行缩放。
        /**
         * 1、如果inPreferredConfig不为null，解码器会尝试使用此参数指定的颜色模式来对图片进行解码，
         *  如果inPreferredConfig为null或者在解码时无法满足此参数指定的颜色模式，解码器会自动根据原始图片的特征以及当前设备的屏幕位深，
         *  选取合适的颜色模式来解码，例如，如果图片中包含透明度，那么对该图片解码时使用的配置就需要支持透明度，默认会使用ARGB_8888来解码。
         * 2、也就是说inPreferredConfig指定的配置并非是一个强制选项，而是建议的（preferred）选项，
         *  Android在实际解码时会参考此参数的配置，但如果此配置不满足，Android会重新选取一个合适的配置来对图片进行解码。
         */
        options.inPreferredConfig=Bitmap.Config.ARGB_4444;//参数inpreferredconfig表示图片解码时使用的颜色模式，也就是图片中每个像素颜色的表示方式。
//        options.inDensity=;//像素压缩比
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bitmapfactory,options);
        int count = bitmap.getByteCount();
        Logs.e(TAG + " 图片所占内存字节数count:", count + "");
        int width=bitmap.getWidth();
        Logs.e(TAG+" 位图width:",width+"");
        int height=bitmap.getHeight();
        Logs.e(TAG+" 位图height:",height+"");

        Logs.e(TAG+" bitmapSize图片所占内存字节数:",getBitmapSize(bitmap)+"");
        imageView.setImageBitmap(bitmap);

    }

    /**
     * 获取bitmap在内存中占得大小
     * @param bitmap
     * @return
     */
    public  int getBitmapSize(Bitmap bitmap) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {  //SInce API 19
            return bitmap.getAllocationByteCount();
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) { //Since API 12
            return bitmap.getByteCount();
        }

        return bitmap.getRowBytes() * bitmap.getHeight();
    }

    /**
     * 1、通过资源ID
     */
    private Bitmap getBitmapFromResource(Resources res, int resId) {
        return BitmapFactory.decodeResource(res, resId);
    }

    /**
     *  2、通过文件
     */
    private Bitmap getBitmapFromFile(String pathName) {
        return BitmapFactory.decodeFile(pathName);
    }

    /**
     *  3、字节数组
     */
    public Bitmap Bytes2Bimap(byte[] b) {
        if (b.length != 0) {
            return BitmapFactory.decodeByteArray(b, 0, b.length);
        } else {
            return null;
        }
    }

    /**
     *  4、通过输入流
     */

    private Bitmap getBitmapFromStream(InputStream inputStream) {
        return BitmapFactory.decodeStream(inputStream);
    }


    @Override
    protected BasePresenter loadPresenter() {
        return null;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }
}
