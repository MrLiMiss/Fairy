package com.tengfei.fairy.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * @ Description :  手机验证码  实践（数字）
 * @ Author 李腾飞
 * @ Time 2020/12/21   17:50
 * @ Version :
 */
public class VerificationCodeView extends View {
    public Context context;

    public VerificationCodeView(Context context) {
        super(context);
    }

    public VerificationCodeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }


}
