package com.tengfei.fairy.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

/**
 * @ Description :自定义音乐播放器service
 * @ Author 李腾飞
 * @ Time 2021/7/9   17:16
 * @ Version :
 */
public class MusicService  extends Service {

    public static final String TAG=MusicService.class.getSimpleName();

    private SimpleBinder mBinder;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG,"onCreate()");
        Log.e(TAG, "MusicService-----" + Thread.currentThread().getName() + "--" + Thread.currentThread().getId());
        mBinder=new SimpleBinder();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {//service 启动时呗调用
        Log.d(TAG,"onStartCommand()");
        new Thread(new Runnable() {//service 处于主线程，但是可在service中起子线程，完成耗时操作。
            @Override
            public void run() {
                // 耗时任务
                Log.d(TAG,"onStartCommand-子线程");
            }
        }).start();
        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        //只有在即没有和任何Activity绑定又处于停止状态下的时候，才可以被摧毁。
        Log.d(TAG, "onDestroy");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {//应用程序通过IBinder 与service 组件  通信
        Log.d(TAG, "onBind");
        if (mBinder != null) {
            return mBinder;
        }
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {//当service上绑定的所有客户端都断开连接时回调该方法
        Log.d(TAG, "onUnbind");
        return super.onUnbind(intent);
    }

    public class SimpleBinder extends Binder {

        public void doTask() {
            Log.d(TAG, "doTask");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    //子线程耗时操作。
                    Log.d(TAG,"SimpleBinder-子线程");
                }
            }).start();
        }
    }
}
