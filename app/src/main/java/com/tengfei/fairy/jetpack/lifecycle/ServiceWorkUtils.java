package com.tengfei.fairy.jetpack.lifecycle;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

import com.tengfei.fairy.utils.Logs;

/**
 * @ Description :
 * @ Author 李腾飞
 * @ Time 1/19/22   2:09 PM
 * @ Version :
 */
public class ServiceWorkUtils implements LifecycleObserver {
    public static  String TAG=ServiceWorkUtils.class.getSimpleName();
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void create(){
        Logs.d(TAG,"Service create:---");
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void resume(){
        Logs.d(TAG,"Service  resume:---");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void pause(){
        Logs.d(TAG,"Service  pause:---");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void stop(){
        Logs.d(TAG,"Service  stop:---");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void destory(){
        Logs.d(TAG,"Service  destory:---");
    }
}
