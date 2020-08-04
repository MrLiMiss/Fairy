package com.tengfei.fairy.utils;

/**
 * @ Description :log工具类
 * @ Author 李腾飞
 * @ Time 2020-08-04   14:02
 * @ Version :
 */

import android.util.Log;
public  class LogUtils {

    public static final boolean isDebug = true;
    private static final String LOG_KEY = "log_weike_";

    public static void i(Class tag, String logStr) {
        if (isDebug) {
            Log.i(LOG_KEY + tag.getSimpleName(), logStr);
        }
    }

    public static void d(Class tag, String logStr) {
        if (isDebug) {
            Log.d(LOG_KEY + tag.getSimpleName(), logStr);
        }
    }

    public static void e(Class tag, String logStr) {
        if (isDebug) {
            Log.e(LOG_KEY + tag.getSimpleName(), logStr);
        }
    }

    public static void e(String tag, String logStr) {
        if (isDebug) {
            Log.e(LOG_KEY + tag, logStr);
        }
    }
}
