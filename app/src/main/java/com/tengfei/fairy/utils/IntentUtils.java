package com.tengfei.fairy.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

import com.tengfei.fairy.activity.MainActivity;
import com.tengfei.fairy.activity.TripartyActivity;
import com.tengfei.fairy.activity.myView.MyViewActivity;
import com.tengfei.fairy.activityLificycle.Aactivity;
import com.tengfei.fairy.activityLificycle.AboutActivity;
import com.tengfei.fairy.customView.CustomViewActivity;
import com.tengfei.fairy.eventbus.EventBus2Activity;
import com.tengfei.fairy.eventbus.EventBusActivity;
import com.tengfei.fairy.activityLificycle.ConfigChangeActivity;
import com.tengfei.fairy.androidBase.konwledge.KnowledgeActivity;
import com.tengfei.fairy.liveData.LiveDataTestActivity;
import com.tengfei.fairy.manager.ManagerTestActivity;
import com.tengfei.fairy.muti_thread.MultiThreadActivity;
import com.tengfei.fairy.service.MusicService;
import com.tengfei.fairy.service.MyIntentService;
import com.tengfei.fairy.service.ServiceTestActivity;
import com.tengfei.fairy.touch.TouchDataActivity;
import com.tengfei.fairy.wedget.sign.SignNameActivity;

import static android.content.Context.BIND_AUTO_CREATE;

/**
 * @ Description :intent 跳转工具类
 * @ Author 李腾飞
 * @ Time 2020-08-04   14:29
 * @ Version :
 */
public class IntentUtils {

    /**
     * 跳转到主页
     * @param mContext
     */
    public static void toMainActivity(Context mContext){
        Intent intent = new Intent(mContext, MainActivity.class);
        mContext.startActivity(intent);
    }

    public static void toEventBus2Activity(Context mContext) {
        Intent intent = new Intent(mContext, EventBus2Activity.class);
        mContext.startActivity(intent);
    }

    public static void toEventBusActivity(Context mContext) {
        Intent intent = new Intent(mContext, EventBusActivity.class);
        mContext.startActivity(intent);
    }

    public static void toConfigChenagedActivity(Context mContext) {
        Intent intent=new Intent(mContext, ConfigChangeActivity.class);
        mContext.startActivity(intent);
    }

    public static void toMyViewActivity(Context context) {
        Intent intent=new Intent(context, MyViewActivity.class);
        context.startActivity(intent);
    }

    /**
     * 跳转到activity 相关页面
     * @param context
     */
    public static void toAboutActivity(Context context) {
        Intent intent=new Intent(context, AboutActivity.class);
        context.startActivity(intent);
    }

    /**
     * activity 跳转生命周期相关
     * @param context
     */
    public static void toLifeCycleActivity(Context context) {
        Intent intent=new Intent(context, Aactivity.class);
        context.startActivity(intent);
    }

    /**
     * 跳转到自定义view
     * @param context
     */
    public static void toCustomActivity(Context context) {
        Intent intent=new Intent(context, CustomViewActivity.class);
        context.startActivity(intent);
    }

    /**签名控件
     * @param context
     */
    public static void toSignActivity(Context context) {
        Intent intent=new Intent(context, SignNameActivity.class);
        context.startActivity(intent);
    }


    /**
     * 启动serviceTestActivity
     * @param context
     */
    public static void toServiceActivity(Context context) {
        Intent intent=new Intent(context,ServiceTestActivity.class);
        context.startActivity(intent);
    }


    /** 启动 音乐service
     * @param context
     */
    public static void startMusicService(Context context) {
        Intent startIntent=new Intent(context,MusicService.class);
        //startService 调用者与service 不绑定，访问者退出，service 仍运行（bindService 与service绑定，访问者退出 service 也终止）
        context.startService(startIntent);
    }

    /** 关闭 音乐service
     * @param context
     */
    public static void endMusicService(Context context) {
        Intent stopIntent=new Intent(context,MusicService.class);
        context.stopService(stopIntent);

    }


    private static MusicService.SimpleBinder mBinder;
    public static final ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {//当activity 与 service链接成功时执行
            mBinder = (MusicService.SimpleBinder) service;
            mBinder.doTask();
            Logs.d("MusicService","ServiceConnection-onServiceConnected");
        }
        @Override
        public void onServiceDisconnected(ComponentName name) {////当activity 与 service断开时执行
            Logs.d("MusicService","ServiceConnection-onServiceDisconnected");
        }
    };

    /**绑定 MusicService
     * @param context
     */
    public static void bindMusicService(Context context) {
        Intent bindIntent=new Intent(context,MusicService.class);
        //BIND_AUTO_CREATE标记，此标记表示在Activity和Service建立关联后自动创建Service，该参数的值可指定为0（不自动创建）
        // 并执行Service中的onCreate方法，并不会执行onStartCommand方法。
        context.bindService(bindIntent,mConnection,BIND_AUTO_CREATE);

    }


    /**解绑 MusicService
     * @param context
     */
    public static void unBindMusicService(Context context) {
             //service 必须处于绑定状态，才可执行unbindService，否则报错 service  not registered
            context.unbindService(mConnection);

    }

    /** 启动 IntentService
     * @param context
     */
    public static void startIntentService(Context context) {
        Intent intent=new Intent(context, MyIntentService.class);
        context.startService(intent);
    }

    /** 关闭 IntentService
     * @param context
     */
    public static void endIntentService(Context context) {
        Intent intent=new Intent(context,MyIntentService.class);
        context.stopService(intent);
    }

    /** 跳转到 系统 Manager 测试页面
     * @param context
     */
    public static void toManagerActivity(Context context) {
        Intent intent =new Intent(context, ManagerTestActivity.class);
        context.startActivity(intent);
    }

    /**
     * 跳转到liveData 相关测试Activity
     * @param context
     */
    public static void toLiveDataActivity(Context context) {
        Intent intent =new Intent(context, LiveDataTestActivity.class);
        context.startActivity(intent);
    }

    /**
     * 跳转到 Touch 测试相关页面
     * @param context
     */
    public static void toTouchActivity(Context context) {
        Intent intent =new Intent(context, TouchDataActivity.class);
        context.startActivity(intent);
    }

    /**
     * 跳转到第三方框架测试页面
     * @param context
     */
    public static void toTripartyActivity(Context context) {
        Intent intent =new Intent(context, TripartyActivity.class);
        context.startActivity(intent);
    }

    /** 跳转多线程
     * @param context
     */
    public static void toMutiThread(Context context) {
        Intent intent=new Intent(context, MultiThreadActivity.class);
        context.startActivity(intent);
    }

    /** 跳转到基础知识
     * @param context
     */
    public static void toKnowledgeActivity(Context context) {
        Intent intent=new Intent(context, KnowledgeActivity.class);
        context.startActivity(intent);
    }
}
