package com.tengfei.fairy.utils.image;

import android.content.Context;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.tengfei.fairy.R;
import com.tengfei.fairy.utils.AndroidUtils;

/**
 * @ Description :图片加载工具类
 * @ Author 李腾飞
 * @ Time 2020-11-18   11:12
 * @ Version :
 */
public class ImageManager {

    /**
     * 带默认图片
     *
     * @param context
     * @param url
     * @param imageView
     */
    public static void loadImgFromUrl(Context context, String url, ImageView imageView) {
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(R.drawable.banner_load)
                .error(R.drawable.banner_load)
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        RequestListener listener = new RequestListener() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, com.bumptech.glide.request.target.Target target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Object resource, Object model, Target target, DataSource dataSource, boolean isFirstResource) {
                return false;
            }
        };

        RequestBuilder builder = Glide.with(context)
                .load(url);
        builder.listener(listener);
        builder.apply(requestOptions)
                .into(imageView)
        ;
    }

    /**
     * 传入默认图片
     *
     * @param context
     * @param url
     * @param imageView
     * @param drawableId
     */
    public static void loadImgFromUrl(Context context, String url, ImageView imageView, int drawableId) {
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(drawableId)
                .error(drawableId)
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.ALL);

        RequestListener listener = new RequestListener() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, com.bumptech.glide.request.target.Target target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Object resource, Object model, com.bumptech.glide.request.target.Target target, com.bumptech.glide.load.DataSource dataSource, boolean isFirstResource) {
                return false;
            }
        };

        RequestBuilder builder = Glide.with(context)
                .load(url);
        builder.listener(listener);

        builder.apply(requestOptions)
                .into(imageView);
    }


    public static void loadImgFromResource(Context context, int resourceId, ImageView imageView) {
        Glide.with(context)
                .load(resourceId)

                .into(imageView);
    }
    //极简版设置图片圆角，以及弧度
    public static void showOldImageRoundBanner(Context context, String url, final ImageView imageView, int radius) {
        RequestOptions myOptions = new RequestOptions()
                .placeholder(R.drawable.banner_load)//占位图
                .error(R.drawable.banner_load) //错误图片
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.ALL);

        Glide.with(context)
                .load(url)
                .apply(myOptions)
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(AndroidUtils.dip2px(context, radius))))
                .into(imageView);

    }

    //设置图片圆角，以及弧度
    public static void showImageRoundBanner(Context context, String url, final ImageView imageView, int radius) {
        RequestOptions myOptions = new RequestOptions()
                .placeholder(R.drawable.banner_load)
                .error(R.drawable.banner_load)
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.ALL);

        Glide.with(context)
                .load(url)
                .apply(myOptions)
                .apply(RequestOptions.bitmapTransform(new RoundedCorners(AndroidUtils.dip2px(context, radius))))
                .into(imageView);

    }

    //设置图片圆角，以及弧度
    public static void showImageRound(Context context, String url, final ImageView imageView, int radius) {
        RequestOptions myOptions = new RequestOptions()
                .error(R.drawable.banner_load);

        Glide.with(context)
                .load(url)
                .apply(myOptions)
                .apply(new RequestOptions()
                        .transforms(new CenterCrop(), new RoundedCorners(AndroidUtils.dip2px(context, radius))
                        ))
                .into(imageView);

    }

    //设置图片圆角，以及弧度
    public static void showImageRound(Context context, int resouceId, final ImageView imageView, int radius) {
        RequestOptions myOptions = new RequestOptions()
                .error(R.drawable.banner_load);

        Glide.with(context)
                .load(resouceId)
                .apply(myOptions)
                .apply(new RequestOptions()
                        .transforms(new CenterCrop(), new RoundedCorners(AndroidUtils.dip2px(context, radius))
                        ))
                .into(imageView);

    }


    public static void loadImgFromResouceWH(Context context, int resourceId, final ImageView imageView) {
        Glide.with(context)
                .load(resourceId)
                .into(imageView)
        ;

    }
}
