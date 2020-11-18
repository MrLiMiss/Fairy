package com.tengfei.fairy.wedget.wuliView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import butterknife.ButterKnife;

/**
 *  Description :自定义化LinearLayout
 * @author  李腾飞
 * @date 2019/4/13
 */

public abstract class CustomLinearLayout extends LinearLayout {
    protected Context mContext;
    public CustomLinearLayout(Context context) {
        this(context, null);
    }

    public CustomLinearLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        LayoutInflater.from(context).inflate(getLayout(), this, true);
        ButterKnife.bind(this);
        setOrientation(VERTICAL);
        init(attrs);
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

    }


    public abstract int getLayout();

    public abstract void init(AttributeSet attrs);


}
