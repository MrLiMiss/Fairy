package com.tengfei.fairy.jetpack;

import androidx.lifecycle.LifecycleService;

import com.tengfei.fairy.jetpack.lifecycle.ServiceWorkUtils;

/**
 * @ Description :
 * @ Author 李腾飞
 * @ Time 1/19/22   2:06 PM
 * @ Version :
 */
public class MyService extends LifecycleService {
    public static String TAG = MyService.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        getLifecycle().addObserver(new ServiceWorkUtils());
    }

}
