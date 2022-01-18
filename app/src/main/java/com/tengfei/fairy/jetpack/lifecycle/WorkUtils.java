package com.tengfei.fairy.jetpack.lifecycle;

import android.util.Log;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import com.tengfei.fairy.utils.Logs;

/**
 * @ Description :实现观察者
 * @ Author 李腾飞
 * @ Time 1/17/22   5:58 PM
 * @ Version :
 */
public class WorkUtils implements LifecycleObserver {

    public WorkUtils(){

    }
    public String TAG="LifeCycleActivity";

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void create(){
        Logs.d(TAG,"create");
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void resume(){
        Logs.d(TAG,"resume");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void pause(){
        Logs.d(TAG,"pause");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void stop(){
        Logs.d(TAG,"stop");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void destory(){
        Logs.d(TAG,"destory");
    }
}
