package com.tengfei.fairy.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.tengfei.fairy.activity.MainActivity;
import com.tengfei.fairy.activity.myView.MyViewActivity;
import com.tengfei.fairy.activityLificycle.Aactivity;
import com.tengfei.fairy.activityLificycle.AboutActivity;
import com.tengfei.fairy.eventbus.EventBus2Activity;
import com.tengfei.fairy.eventbus.EventBusActivity;
import com.tengfei.fairy.activityLificycle.ConfigChangeActivity;
import com.tengfei.fairy.wedget.sign.SignNameActivity;

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

    /**签名控件
     * @param context
     */
    public static void toSignActivity(Context context) {
        Intent intent=new Intent(context, SignNameActivity.class);
        context.startActivity(intent);
    }
}
