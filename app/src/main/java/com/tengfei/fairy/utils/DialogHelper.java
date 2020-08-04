package com.tengfei.fairy.utils;

import android.app.Activity;
import android.app.Dialog;
import android.text.Html;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tengfei.fairy.R;

/**
 * @ Description :dialog 工具类
 * @ Author 李腾飞
 * @ Time 2020-08-04   14:45
 * @ Version :
 */
public class DialogHelper {
    private Dialog mDialog;
    private Activity mActivity;
    private TextView msgView;

    public DialogHelper(Activity activity) {
        mActivity = activity;
    }

    /**
     * dialog 标题可以加背景图片的,带有取消和确定按钮
     *
     * @param title                    标题
     * @param msg                      内容
     * @param positive                 确定（右）
     * @param positiveListener         确定监听
     * @param negative                 取消（左）
     * @param negativeListener         取消监听
     * @param isCanceledOnTouchOutside true：点击外部区域示消失dialog
     */
    public void alert(final CharSequence title,
                      final CharSequence msg,
                      final CharSequence positive,
                      final View.OnClickListener positiveListener,
                      final CharSequence negative,
                      final View.OnClickListener negativeListener,
                      final Boolean isCanceledOnTouchOutside) {
        dismissProgressDialog();
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mActivity != null && !mActivity.isFinishing()) {
                    View alertView = LayoutInflater.from(mActivity).inflate(R.layout.view_dialog_common, null);
                    mDialog = new Dialog(mActivity, R.style.alertDialog);
                    DisplayMetrics displayMetrics = mActivity.getResources().getDisplayMetrics();
                    //设置dialog宽高
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                            displayMetrics.widthPixels * 3 / 4,
                            FrameLayout.LayoutParams.WRAP_CONTENT);
                    //设置布局
                    mDialog.setContentView(alertView, layoutParams);

                    TextView titleView = alertView.findViewById(R.id.dialogTitle);
                    msgView = alertView.findViewById(R.id.dialogMsg);

                    Button leftButton = alertView.findViewById(R.id.leftBtn);
                    Button rightButton = alertView.findViewById(R.id.rightBtn);
                    View line = alertView.findViewById(R.id.view_line);

                    //标题
                    if (!TextUtils.isEmpty(title)) {
                        titleView.setText(title);
                    } else {
                        titleView.setVisibility(View.GONE);
                    }

                    //内容
                    if (!TextUtils.isEmpty(msg)) {
                        msgView.setText(Html.fromHtml(msg.toString()));
                    }


                    //右边确定按钮
                    if (!TextUtils.isEmpty(positive)) {
                        rightButton.setText(positive);
                        rightButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mDialog.dismiss();
                                if (null != positiveListener)
                                    positiveListener.onClick(v);
                            }
                        });
                    } else {
                        rightButton.setVisibility(View.GONE);
                    }
                    //左边取消按钮，如果只有一个按钮传null
                    if (!TextUtils.isEmpty(negative)) {
                        leftButton.setText(negative);

                        leftButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mDialog.dismiss();
                                if (null != negativeListener)
                                    negativeListener.onClick(v);
                            }
                        });
                    } else {
                        line.setVisibility(View.GONE);
                        leftButton.setVisibility(View.GONE);
                        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) rightButton.getLayoutParams();
                        params.leftMargin = 0;
                    }

                    try {
                        mDialog.setCancelable(isCanceledOnTouchOutside);
                        mDialog.setCanceledOnTouchOutside(isCanceledOnTouchOutside);
                        mDialog.show();
                    } catch (Exception e) {
                    }
                }
            }
        });
    }

    /**
     * 两个按钮 默认点击外部区域不可消失
     * @param title
     * @param msg
     * @param positive
     * @param positiveListener
     * @param negative
     * @param negativeListener
     */
    public void alert(final CharSequence title,
                      final CharSequence msg,
                      final CharSequence positive,
                      final View.OnClickListener positiveListener,
                      final CharSequence negative,
                      final View.OnClickListener negativeListener) {
        alert(title, msg, positive, positiveListener, negative, negativeListener, false);
    }

    /**
     * 只有一个按钮的
     *
     * @param title
     * @param msg
     * @param positive
     * @param positiveListener
     * @param isCanceledOnTouchOutside
     */
    public void alertSingleButton(final CharSequence title,
                                  final CharSequence msg,
                                  final CharSequence positive,
                                  final View.OnClickListener positiveListener,
                                  final Boolean isCanceledOnTouchOutside) {
        alert(title, msg, positive, positiveListener, null, null, isCanceledOnTouchOutside);
    }

    /**
     * 只有一个按钮的  默认点击外部区域不可消失
     *
     * @param title
     * @param msg
     * @param positive
     * @param positiveListener
     */
    public void alertSingleButton(final CharSequence title,
                                  final CharSequence msg,
                                  final CharSequence positive,
                                  final View.OnClickListener positiveListener) {
        alert(title, msg, positive, positiveListener, null, null, false);
    }

    public void dismissProgressDialog() {
        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                if (mDialog != null && mDialog.isShowing() && !mActivity.isFinishing()) {
                    try {
                        mDialog.dismiss();
                    } catch (Exception e) {
                    } finally {
                        mDialog = null;
                    }
                }
            }
        });
    }

    public void setMsg(String msg) {
        if (mDialog != null && mDialog.isShowing() && msgView != null) {
            msgView.setText(Html.fromHtml(msg));
        }
    }
}
