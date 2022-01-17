package com.tengfei.fairy.widget.Dialog;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.tengfei.fairy.R;

import java.lang.ref.WeakReference;

/**
 * @ Description :加载 dialog
 * @ Author 李腾飞
 * @ Time 2020-11-16   17:16
 * @ Version :
 */
public class CustomLoadingDialog extends Dialog {
    public static WeakReference<Context> contextWeakReference;

    public CustomLoadingDialog(Context context, int theme) {
        super(context, theme);
    }

    public static class Builder {
        private Context context;
        private TextView tips;

        public Builder(Context context) {
            this.context = context;
            contextWeakReference = new WeakReference<Context>(context);

        }

        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
        public CustomLoadingDialog build(String tip) {
            final CustomLoadingDialog dialog = new CustomLoadingDialog(context, R.style.CustomLoadingDialog);
            View dialogView = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                    .inflate(R.layout.view_common_loading, null);
            tips = dialogView.findViewById(R.id.loading_tips);
            tips.setText(tip);
            dialog.setContentView(dialogView);
            dialog.setCanceledOnTouchOutside(false);
            return dialog;
        }

        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
        public CustomLoadingDialog build() {
            final CustomLoadingDialog dialog = new CustomLoadingDialog(context, R.style.CustomLoadingDialog);
            View dialogView = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                    .inflate(R.layout.view_common_loading, null);
            dialog.setContentView(dialogView);
            dialog.setCanceledOnTouchOutside(false);
            return dialog;
        }
    }
    private static CustomLoadingDialog loadingDialog;
    public static void showLoading() {
        if (null!= contextWeakReference.get()) {
            Activity context = (Activity) contextWeakReference.get();
            context.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (loadingDialog == null) {
                        loadingDialog = new CustomLoadingDialog.Builder(context).build();
                    }

                    if (!loadingDialog.isShowing()) {
                        loadingDialog.show();
                    }
                }
            });

        }
    }

    public static void dissmissLoading(){
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
    }
}

