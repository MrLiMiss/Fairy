package com.tengfei.fairy.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.tengfei.fairy.R;

/**
 * @ Description :
 * @ Author 李腾飞
 * @ Time 2020-04-28   15:25
 * @ Version :
 */
public abstract class ToastTools {

    private ToastTools() throws IllegalAccessException {
        throw new IllegalAccessException("工具类无法实例化!");
    }

    public static void showLong(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

    public static void showShort(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * Toast显示
     */
    static Toast toast=null;
    public static void showToast(Context ctx, String msg){
        if(toast==null){
            toast= Toast.makeText(ctx, msg, Toast.LENGTH_LONG);
        }else{
            toast.setText(msg);
        }
        toast.show();
    }

    public static void cancel(){
        if(toast != null){
            toast.cancel();
        }
    }

    private static Toast blackBgToast=null;
    public static void showCenterToast(Context context, String msg){
        if(blackBgToast != null){
            blackBgToast.cancel();
            blackBgToast = null;
        }
        blackBgToast = new Toast(context.getApplicationContext());
        View toastView = LayoutInflater.from(context).inflate(R.layout.toast_black_bg,null);
        TextView tvToast = (TextView) toastView.findViewById(R.id.tv_toast);
        tvToast.setText(msg);
        blackBgToast.setView(toastView);
        blackBgToast.setGravity(Gravity.CENTER,0,0);
        blackBgToast.show();
    }
}
