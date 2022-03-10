package com.tengfei.fairy.oom.singleIntense;

/**
 * @ Description :
 * @ Author 李腾飞
 * @ Time 2022/3/10   7:43 PM
 * @ Version :
 */
import android.content.Context;
import android.util.Log;

public class SingleInstance {
    private Context mContext;
    private static SingleInstance instance;

    private SingleInstance(Context context) {
        this.mContext = context;
    }

    public static SingleInstance getInstance(Context context) {
        if (instance == null) {
            instance = new SingleInstance(context);
        }
        return instance;
    }

    public void say() {
        Log.i("tag", "this is single instance");
        Log.i("tag", "：code：" + instance.hashCode());
    }
}
