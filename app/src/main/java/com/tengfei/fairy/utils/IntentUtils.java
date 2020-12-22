package com.tengfei.fairy.utils;

import android.content.Context;
import android.content.Intent;

import com.tengfei.fairy.activity.MainActivity;
import com.tengfei.fairy.activity.myView.MyViewActivity;
import com.tengfei.fairy.eventbus.EventBus2Activity;
import com.tengfei.fairy.eventbus.EventBusActivity;
import com.tengfei.fairy.test.ConfigChangeActivity;

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
}
