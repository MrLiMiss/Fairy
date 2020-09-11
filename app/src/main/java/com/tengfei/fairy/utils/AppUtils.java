package com.tengfei.fairy.utils;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.util.LruCache;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;

import com.tengfei.fairy.constant.Constants;

import java.io.ByteArrayOutputStream;

/**
 * @ Description :AppUtils
 * @ Author 李腾飞
 * @ Time 2020-08-04   14:00
 * @ Version :
 */
public class AppUtils {

    public static void logOutClearInfo() {
        //清空token
        SharePreferenceUtil.setInfoToShared(Constants.SPKey.TOKEN, "");
        SharePreferenceUtil.setInfoToShared(Constants.SPKey.USER_NAME, "");
    }

    /**
     * 判断是什么设备
     *
     * @param device huawei/xiaomi/meizu
     * @return
     */
    public static boolean isDevice(String device) {
        String manufacturer = Build.MANUFACTURER;
        if (device.equalsIgnoreCase(manufacturer)) {
            return true;
        }
        return false;
    }


    /**
     * RecyclerView截屏
     *
     * @param view
     * @return
     */
    public static Bitmap shotRecyclerView(RecyclerView view) {
        RecyclerView.Adapter adapter = view.getAdapter();
        Bitmap bigBitmap = null;
        if (adapter != null) {
            int size = adapter.getItemCount();
            int height = 0;
            Paint paint = new Paint();
            int iHeight = 0;
            final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);

            // Use 1/8th of the available memory for this memory cache.
            final int cacheSize = maxMemory / 8;
            LruCache<String, Bitmap> bitmaCache = new LruCache<>(cacheSize);
            for (int i = 0; i < size; i++) {
                RecyclerView.ViewHolder holder = adapter.createViewHolder(view, adapter.getItemViewType(i));
                adapter.onBindViewHolder(holder, i);
                holder.itemView.measure(
                        View.MeasureSpec.makeMeasureSpec(view.getWidth(), View.MeasureSpec.EXACTLY),
                        View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
                holder.itemView.layout(0, 0, holder.itemView.getMeasuredWidth(),
                        holder.itemView.getMeasuredHeight());
                holder.itemView.setDrawingCacheEnabled(true);
                holder.itemView.buildDrawingCache();
                Bitmap drawingCache = holder.itemView.getDrawingCache();
                if (drawingCache != null) {

                    bitmaCache.put(String.valueOf(i), drawingCache);
                }
                height += holder.itemView.getMeasuredHeight();
            }

            bigBitmap = Bitmap.createBitmap(view.getMeasuredWidth(), height, Bitmap.Config.ARGB_8888);
            Canvas bigCanvas = new Canvas(bigBitmap);
            Drawable lBackground = view.getBackground();
            if (lBackground instanceof ColorDrawable) {
                ColorDrawable lColorDrawable = (ColorDrawable) lBackground;
                int lColor = lColorDrawable.getColor();
                bigCanvas.drawColor(lColor);
            }

            for (int i = 0; i < size; i++) {
                Bitmap bitmap = bitmaCache.get(String.valueOf(i));
                bigCanvas.drawBitmap(bitmap, 0f, iHeight, paint);
                iHeight += bitmap.getHeight();
                bitmap.recycle();
            }
        }
        return bigBitmap;
    }

    /**
     * 截图
     */
    public static Bitmap shotScrollView(NestedScrollView scrollView, int height) {
        int h = 0;
        Bitmap bitmap = null;
        LogUtils.e(AppUtils.class, "scrollview childcount = " + scrollView.getChildCount());
        if (height == 0) {
            for (int i = 0; i < scrollView.getChildCount(); i++) {
                h += scrollView.getChildAt(i).getHeight();
                scrollView.getChildAt(i).setBackgroundColor(Color.parseColor("#ffffff"));
            }
        } else {
            h = height;
        }
        LogUtils.e(AppUtils.class, "Scrollview height = " + h);
        bitmap = Bitmap.createBitmap(scrollView.getWidth(), h, Bitmap.Config.RGB_565);
        final Canvas canvas = new Canvas(bitmap);
        scrollView.draw(canvas);
        LogUtils.e(AppUtils.class, "shotScrollView img size = " + getBitmapSize(bitmap));
        return bitmap;
    }


    public static Bitmap compressQuality(Bitmap bm, int quality) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, quality, bos);
        byte[] bytes = bos.toByteArray();
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;
        Bitmap mSrcBitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);
//        LogUtils.e(AppUtils.class, "compress img size = " + getBitmapSize(mSrcBitmap));
        return mSrcBitmap;
    }


    /**
     * 纵向拼接
     * <功能详细描述>
     *
     * @param first
     * @param second
     * @return 
     */
    public static Bitmap addBitmap(Bitmap first, Bitmap second) {
        int width = Math.max(first.getWidth(), second.getWidth());
        int height = first.getHeight() + second.getHeight();
        Bitmap result = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);

        Canvas canvas = new Canvas(result);
        canvas.drawBitmap(first, 0, 0, null);
        canvas.drawBitmap(second, first.getHeight(), 0, null);
        return result;
    }

    public static int getBitmapSize(Bitmap bitmap) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {  //SInce API 19
            return bitmap.getAllocationByteCount();
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) { //Since API 12
            return bitmap.getByteCount();
        }

        return bitmap.getRowBytes() * bitmap.getHeight();
    }

    /**
     * 打开相册
     * 打开指定的图片
     *
     * @param activity
     * @param uri
     */
    public static void openAlbumWithPic(Activity activity, Uri uri) {
        //使用Intent
        Intent intent = new Intent(Intent.ACTION_VIEW);
        //Uri mUri = Uri.parse("file://" + picFile.getPath());Android3.0以后最好不要通过该方法，存在一些小Bug
        intent.setDataAndType(uri, "image/*");
        activity.startActivity(intent);

    }


    public static void hideNavigationBar(Activity activity) {

        Window window = activity.getWindow();

        WindowManager.LayoutParams params = window.getAttributes();

        params.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE | View.SYSTEM_UI_FLAG_FULLSCREEN;

        window.setAttributes(params);

        int uiFlags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE

                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION

                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN

                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar

                | View.SYSTEM_UI_FLAG_FULLSCREEN; // hide status bar

        if (Build.VERSION.SDK_INT >= 19) {

            uiFlags |= 0x00001000;  //SYSTEM_UI_FLAG_IMMERSIVE_STICKY: hide navigation bars - compatibility: building API level is lower thatn 19, use magic number directly for higher API target level

        } else {

            uiFlags |= View.SYSTEM_UI_FLAG_LOW_PROFILE;

        }

        activity.getWindow().getDecorView().setSystemUiVisibility(uiFlags);

    }

    /**
     * 初始化webview设置
     *
     * @param mWebView
     * @param webChromeClient
     * @param webViewClient
     */
    public static void initWebView(WebView mWebView, WebChromeClient webChromeClient, WebViewClient webViewClient) {
        initWebView(mWebView, webChromeClient, webViewClient, false);
    }

    public static void initWebView(WebView mWebView, WebChromeClient webChromeClient, WebViewClient webViewClient, boolean supportZoom) {
        //缩放大小
        mWebView.setInitialScale(100);
        mWebView.setFocusable(true);
        mWebView.setClickable(true);
        mWebView.setFocusableInTouchMode(true);
        mWebView.requestFocus(View.FOCUS_DOWN);
        mWebView.setBackgroundColor(0x00000000);
        //webSetting
        WebSettings mWebSettings = mWebView.getSettings();
        //5.0+设置混合模式。允许https 访问http
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mWebSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        mWebSettings.setDefaultTextEncodingName("UTF-8");
        //支持JS
        mWebSettings.setJavaScriptEnabled(true);
        //是否开启本地DOM存储
        mWebSettings.setDomStorageEnabled(true);
        //自适应屏幕  使用预览模式加载网页
        mWebSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        mWebSettings.setLoadWithOverviewMode(true);
        //允许访问文件
        mWebSettings.setAllowFileAccess(true);
        mWebSettings.setDatabaseEnabled(true);
        mWebSettings.setGeolocationEnabled(true);
        mWebSettings.setAppCacheEnabled(true);
        mWebSettings.setSaveFormData(false);
        //允许js弹出窗口
        mWebSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        //开启缓存
        mWebSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        //支持手势缩放
        mWebSettings.setSupportZoom(supportZoom);
        //使用其内置的变焦机制
        mWebSettings.setBuiltInZoomControls(supportZoom);
        //扩大比例缩放
        mWebSettings.setUseWideViewPort(true);
        //手势缩放不显示缩放控件
        mWebSettings.setDisplayZoomControls(false);


        mWebSettings.setPluginState(WebSettings.PluginState.ON);
        //进度的显示等等
        if (webChromeClient != null) {
            mWebView.setWebChromeClient(webChromeClient);
        }
        //获得web请求的返回
        if (webViewClient != null) {
            mWebView.setWebViewClient(webViewClient);
        }
        mWebView.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimeType, long contentLength) {
            }

        });

        CookieSyncManager cookieSyncManager = CookieSyncManager.createInstance(mWebView.getContext());
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        cookieManager.removeSessionCookie();
        cookieManager.removeAllCookie();
        cookieSyncManager.sync();
    }

    public static void destroyWebView(WebView mWebView) {
        if (mWebView != null) {
            ViewParent parent = mWebView.getParent();
            if (parent != null) {
                ((ViewGroup) parent).removeView(mWebView);
            }

            mWebView.stopLoading();
            mWebView.getSettings().setJavaScriptEnabled(false);
            mWebView.clearHistory();
            mWebView.clearView();
            mWebView.removeAllViews();
            mWebView.destroy();
        }
    }

}
