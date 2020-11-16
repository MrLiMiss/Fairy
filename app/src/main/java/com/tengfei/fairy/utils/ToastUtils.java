package com.tengfei.fairy.utils;

import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.tengfei.fairy.R;
import com.tengfei.fairy.application.FairyApplication;

/**
 * @ Description :
 * @ Author 李腾飞
 * @ Time 2020-11-16   16:55
 * @ Version :
 */
public class ToastUtils {
    private static Toast mToast;
    private static Handler mHandler = new Handler(Looper.getMainLooper());

    public static void showToast(String msg) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                /*if (mToast != null) {
                    mToast.cancel();
                }
                mToast = Toast.makeText(WeiKeApplication.getApplication().getApplicationContext(), msg, Toast.LENGTH_SHORT);
                mToast.show();*/

                if (mToast != null) {
                    mToast.cancel();
                }
                mToast = new Toast(FairyApplication.getApplication().getApplicationContext());
                View toastView = LayoutInflater.from(FairyApplication.getApplication().getApplicationContext()).inflate(R.layout.toast_bg, null,false);
                TextView tvToastContent = toastView.findViewById(R.id.tv_toastContent);
                tvToastContent.setText(msg);
                mToast.setView(toastView);
                mToast.setDuration(Toast.LENGTH_SHORT);

                mToast.setGravity(Gravity.CENTER, 0, 0);
                mToast.show();

            }
        });
    }

    /**
     * 展示在中间
     *
     * @param msg
     */
    @Deprecated
    private  void showToastCenter(String msg) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                if (mToast != null) {
                    mToast.cancel();
                }
                //mToast = Toast.makeText(WeiKeApplication.getApplication().getApplicationContext(), msg, Toast.LENGTH_SHORT);
                mToast = new Toast(FairyApplication.getApplication().getApplicationContext());
                View toastView = LayoutInflater.from(FairyApplication.getApplication().getApplicationContext()).inflate(R.layout.toast_bg, null);
                TextView tvToastContent = toastView.findViewById(R.id.tv_toastContent);
                tvToastContent.setText(msg);
                mToast.setView(toastView);
                mToast.setDuration(Toast.LENGTH_SHORT);

                mToast.setGravity(Gravity.CENTER, 0, 0);
                mToast.show();
            }
        });
    }
}

