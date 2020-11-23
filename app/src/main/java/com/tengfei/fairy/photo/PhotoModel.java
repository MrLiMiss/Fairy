package com.tengfei.fairy.photo;

import android.util.Log;


/**
 * @ Description :图片相关model
 * @ Author 李腾飞
 * @ Time 2020-11-23   13:37
 * @ Version :
 */
public class PhotoModel implements PhotoContract.Model {
    public static String TAG=PhotoModel.class.getSimpleName();



    @Override
    public void getPhoto(String userName, int phototype, PhotoContract.ResponseCallback<PhotoBean> callback) {
        Log.i(TAG,"请求图片数据");
    }

    @Override
    public void clearAllCall() {
        Log.i(TAG,"清除所有Call");

    }
}
