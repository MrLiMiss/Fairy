package com.tengfei.fairy.application;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ProcessLifecycleOwner;
import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

import com.tengfei.fairy.config.FileConfig;
import com.tengfei.fairy.jetpack.lifecycle.AppWorkUtils;

/**
 * @ Description :全局application
 * @ Author 李腾飞
 * @ Time 2020-08-04   10:30
 * @ Version :
 */
public class FairyApplication extends MultiDexApplication implements Application.ActivityLifecycleCallbacks  {
    private static FairyApplication mApplication;
    private static Context context;

    public static FairyApplication getInstance(){
        return mApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        context = this;
        ProcessLifecycleOwner.get().getLifecycle().addObserver(new AppWorkUtils());

        //初始化FileConfig
        FileConfig.getInstance().initFileConf(this);

    }


    public static Application getApplication() {
        return mApplication;
    }

    public static Context getContext() {
        return context;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
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
