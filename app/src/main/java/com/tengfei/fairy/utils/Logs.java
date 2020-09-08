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
 * @author 刘国山 lgs@yitong.com.cn
 * @version 1.0 2012-8-15
 * @Description
 * @class com.yitong.zjrc.mbank.android.logs.Logs
 */
public class Logs {

    private static final boolean VERBOSE = true;
    private static final boolean DEBUG = true;
    private static final boolean INFO = true;
    private static final boolean WARN = true;
    private static final boolean ERROR = true;


    public static void v(String mess) {
        if (BuildConfig.DEBUG) { Log.v(getTag(), buildMessage(mess)); }
    }
    public static void d(String mess) {
        if (BuildConfig.DEBUG) { Log.d(getTag(), buildMessage(mess)); }
    }
    public static void i(String mess) {
        if (BuildConfig.DEBUG) { Log.i(getTag(), buildMessage(mess)); }
    }
    public static void w(String mess) {
        if (BuildConfig.DEBUG) { Log.w(getTag(), buildMessage(mess)); }
    }
    public static void e(String mess) {
        if (BuildConfig.DEBUG) { Log.e(getTag(), buildMessage(mess)); }
    }

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

    private static String buildMessage(String msg) {
        StackTraceElement[] trace = new Throwable().fillInStackTrace()
                .getStackTrace();
        String caller = "";
        for (int i = 2; i < trace.length; i++) {
            Class<?> clazz = trace[i].getClass();
            if (!clazz.equals(Logs.class)) {
                caller = trace[i].getMethodName();
                break;
            }
        }
        return String.format(Locale.US, "[%d] %s: %s", Thread.currentThread()
                .getId(), caller, msg);
    }

    // public static final String TAG_PREFIX = "elife";
    public static boolean isShow = BuildConfig.DEBUG;

    public static void v(String tag, String msg) {
        if (VERBOSE&isShow) {
            android.util.Log.v(tag, msg);
        }
    }

    public static void v(String tag, String msg, Throwable tr) {
        if (VERBOSE&isShow) {
            android.util.Log.v(tag, msg, tr);
        }
    }

    public static void d(String tag, String msg) {
        if (DEBUG&isShow) {
            android.util.Log.d(tag, msg);
        }
    }

    public static void d(String tag, String msg, Throwable tr) {
        if (DEBUG&isShow) {
            android.util.Log.d(tag, msg, tr);
        }
    }

    public static void i(String tag, String msg) {
        if (INFO&isShow) {
            android.util.Log.i(tag, msg);
        }
    }

    public static void i(String tag, String msg, Throwable tr) {
        if (INFO&isShow) {
            android.util.Log.i(tag, msg, tr);
        }
    }

    public static void w(String tag, String msg) {
        if (WARN&isShow) {
            android.util.Log.w(tag, msg);
        }
    }

    public static void w(String tag, String msg, Throwable tr) {
        if (WARN&isShow) {
            android.util.Log.w(tag, msg, tr);
        }
    }

    public static void w(String tag, Throwable tr) {
        if (WARN&isShow) {
            android.util.Log.w(tag, tr);
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
