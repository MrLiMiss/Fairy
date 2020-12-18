package com.tengfei.fairy.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

/**
 * Created by mfhj-18 on 16/8/4.
 */

public class ToastUtil {

    private static Handler mHandler = new Handler(Looper.getMainLooper());
    private static Object mLockObject = new Object();
    private static Toast mToast = null;

    public static void showMessage(final Context context, final int msg) {
        showMessage(context, msg, Toast.LENGTH_SHORT);
    }

    public static void showMessage(final Context context, final String msg) {
        showMessage(context, msg, Toast.LENGTH_SHORT);
    }

    public static void showMessage(final Context context, final int msg, final int len) {
        showMessage(context, context.getString(msg), len);
    }

    public static void showMessage(final Context context, final String msg, final int len) {
        new Thread(new Runnable() {
            public void run() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        synchronized (mLockObject) {
                            showToast(context, msg, len);
                        }
                    }
                });
            }
        }).start();
    }

    private static void showToast(final Context context, final String msg, final int len) {
        if (mToast != null) {
            mToast.cancel();
            mToast.setText(msg);
            mToast.setDuration(len);
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mToast.show();
                }
            }, Toast.LENGTH_SHORT);
        } else {
            mToast = Toast.makeText(context, msg, len);
            mToast.show();
        }
    }

    public static void cancelCurrentToast() {
        if (mToast != null) {
            mToast.cancel();
        }
    }
}
