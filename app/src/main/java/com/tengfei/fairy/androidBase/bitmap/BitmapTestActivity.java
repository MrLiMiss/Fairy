package com.tengfei.fairy.androidBase.bitmap;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;

import com.tengfei.fairy.R;
import com.tengfei.fairy.mvp.BaseMvpActivity;
import com.tengfei.fairy.mvp.BasePresenter;
import com.tengfei.fairy.utils.Logs;

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


    @Override
    protected int getLayoutId() {
        return R.layout.activity_bitmap;
    }



    @OnClick({R.id.img_bitmap,R.id.tv_bitmap})
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
            case R.id.tv_cutting:
                bitmapCutting();

        }
    }



    /**
     * bitmap 裁剪
     */
    private void bitmapCutting() {
    }

    /**
     * bitmap 缩小
     */
    private void bitmapNarrow() {
    }

    /**
     * bitmap 放大
     */
    private void bitmapEnlarge() {
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
     */
    private void detailBitmap() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bitmapfactory);
        int count = bitmap.getByteCount();
        Logs.e(TAG + " count:", count + "");
        int width=bitmap.getWidth();
        Logs.e(TAG+" width:",width+"");
        int height=bitmap.getHeight();
        Logs.e(TAG+" height:",height+"");
        imageView.setImageBitmap(bitmap);



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
