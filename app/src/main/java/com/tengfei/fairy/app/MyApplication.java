package com.tengfei.fairy.app;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.multidex.MultiDexApplication;

/**
 * @ Description :MyApplication
 * @ Author 李腾飞
 * @ Time 2021/6/28   13:48
 * @ Version :
 */
public class MyApplication extends MultiDexApplication implements Application.ActivityLifecycleCallbacks {
    private String TAG="MyApplication";
    public static MyApplication myApplication;
    public static MyApplication getInstance(){
        return myApplication;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        initData();
    }

    /**
     * 初始化数据
     */
    private void initData() {
    }

    @Override
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {

    }

    @Override
    public void onActivityStarted(@NonNull Activity activity) {

    }

    @Override
    public void onActivityResumed(@NonNull Activity activity) {

    }

    @Override
    public void onActivityPaused(@NonNull Activity activity) {

    }

    @Override
    public void onActivityStopped(@NonNull Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(@NonNull Activity activity) {

    }
}
