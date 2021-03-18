package com.tengfei.fairy.utils;

import android.util.Log;

/**
 * @ Description :
 * @ Author 李腾飞
 * @ Time 2020-09-04   10:09
 * @ Version :
 */


import com.tengfei.fairy.BuildConfig;

import java.util.Locale;

/**
 * 日志管理工具类
 *
 */
public class Logs {

    private static final boolean VERBOSE = true;
    private static final boolean DEBUG = true;
    private static final boolean INFO = true;
    private static final boolean WARN = true;
    private static final boolean ERROR = true;

    private static String getTag() {
        StackTraceElement[] trace = new Throwable().fillInStackTrace()
                .getStackTrace();
        String callingClass = "";
        for (int i = 2; i < trace.length; i++) {
            Class<?> clazz = trace[i].getClass();
            if (!clazz.equals(Logs.class)) {
                callingClass = trace[i].getClassName();
                callingClass = callingClass.substring(callingClass
                        .lastIndexOf('.') + 1);
                break;
            }
        }
        return callingClass;
    }

    // public static final String TAG_PREFIX = "elife";
    public static boolean isShow = BuildConfig.DEBUG;

    public static void v(String tag, String msg) {
        if (VERBOSE&isShow) {
            android.util.Log.v(tag, msg);
        }
    }


    public static void d(String tag, String msg) {
        if (DEBUG&isShow) {
            android.util.Log.d(tag, msg);
        }
    }


    public static void i(String tag, String msg) {
        if (INFO&isShow) {
            android.util.Log.i(tag, msg);
        }
    }





    public static void e(String tag, String msg) {
        if (ERROR&isShow) {
            android.util.Log.e(tag, msg);
        }
    }

    public static void e(String tag, String msg, Throwable tr) {
        if (ERROR&isShow) {
            android.util.Log.e(tag, msg, tr);
        }
    }
}
