package com.tengfei.fairy.jetpack.lifecycle;

import android.animation.ValueAnimator;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

import com.tengfei.fairy.utils.Logs;

/**
 * @ Description :Application lifecycle 观察者
 * @ Author 李腾飞
 * @ Time 1/18/22   9:58 AM
 * @ Version :
 */
public class AppWorkUtils implements LifecycleObserver {
    public String TAG="AppWorkUtils";

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public void create(){
        Logs.d(TAG,"Application create:---");
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void resume(){
        Logs.d(TAG,"Application  resume:---");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    public void pause(){
        Logs.d(TAG,"Application  pause:---");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void stop(){
        Logs.d(TAG,"Application  stop:---");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void destory(){
        Logs.d(TAG,"Application  destory:---");
    }
}
