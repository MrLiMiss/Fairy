package com.tengfei.fairy.service;

import android.app.IntentService;
import android.content.Intent;

import androidx.annotation.Nullable;

import com.tengfei.fairy.utils.Logs;

/**
 * @ Description :
 * 1、IntentService 创建单独的work线程处理耗时操作（onHandleIntent（）中实现的代码），所有请求结束后service自动结束
 * 2、IntentService 的onBind方法提供了默认实现，返回null
 * 3、IntentService 的 onStartCommand（）提供默认实现，将请求intent 自动添加到队列中。
 * @ Author 李腾飞
 * @ Time 2021/7/14   09:46
 * @ Version :
 */
public class MyIntentService extends IntentService {

    private static final String TAG=MyIntentService.class.getSimpleName();
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     */
    public MyIntentService() {
        super(TAG);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Logs.d(TAG,"onCreate");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {//IntentService 会启动子线程执行onHandleIntent（）方法
        long endTime=System.currentTimeMillis()+10*1000;
        Logs.d(TAG,"onHandleIntent-开始执行");
        while (System.currentTimeMillis() < endTime){
            synchronized (this){
                try {
                    wait(endTime - System.currentTimeMillis());
                }catch (Exception e){
                    Logs.d(TAG,"Exception-"+e.getMessage());
                }
            }
        }
        Logs.d(TAG,"onHandleIntent-执行完毕");

    }

    @Override
    public void onDestroy() {
        Logs.d(TAG,"onDestroy");
        super.onDestroy();
    }
}
