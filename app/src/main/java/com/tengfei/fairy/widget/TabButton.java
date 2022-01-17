package com.tengfei.fairy.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tengfei.fairy.R;

/**
 * Created by niutingting on 2017/1/9.
 * {code}
 * <declare-styleable name="TabButtun">
 * <!--图片资源-->
 * <attr name="button_image" format="reference" />
 * <!--文字-->
 * <attr name="button_text" format="string" />
 * <!--文字颜色-->
 * <attr name="text_color" format="color" />
 * <!--文字字体大小-->
 * <attr name="text_size"  format="dimension"/>
 * <!--selected状态-->
 * <attr name="selected" format="boolean"/>
 * </declare-styleable>
 * {code}
 */

public class TabButton extends LinearLayout {


    private ImageView mTabImage;
    private TextView mTabText;
    private TextView mIndicatorTextView;
    int buttunImageRes;
    String buttonTextContent;

    public TabButton(Context context) {
        super(context);
        init(null);
    }

    public TabButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public TabButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }


    private void init(AttributeSet attrs) {
        setOrientation(LinearLayout.VERTICAL);
        RelativeLayout tabImageContainer = new RelativeLayout(getContext());

        addTabImage(tabImageContainer);
        addIndicatorView(tabImageContainer);
        addTabText(tabImageContainer);

        setTabButton(attrs);
    }

    private void setTabButton(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.TabButtun);
        buttunImageRes = typedArray.getResourceId(R.styleable.TabButtun_button_image, 0);
        int buttonTextColor = typedArray.getResourceId(R.styleable.TabButtun_text_color, 0);
        int textSize = typedArray.getDimensionPixelSize(R.styleable.TabButtun_text_size, (int) getContext().getResources().getDimension(R.dimen.indicator_text_size));
        boolean selected = typedArray.getBoolean(R.styleable.TabButtun_selected, false);
        buttonTextContent = typedArray.getString(R.styleable.TabButtun_button_text);
        if (buttunImageRes != 0) {
            mTabImage.setImageResource(buttunImageRes);
        }
        if (buttonTextColor != 0) {
            mTabText.setTextColor(getResources().getColorStateList(buttonTextColor));
        }
        if (buttonTextContent != null) {
            mTabText.setText(buttonTextContent);
        }
        mTabText.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        setSelected(selected);
    }

    //向Tab加图片
    private void addTabImage(RelativeLayout tabImageContainer) {
        RelativeLayout.LayoutParams tabImageParam = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        //增加图片到relativelayout
        mTabImage = new ImageView(getContext());
        mTabImage.setId(R.id.idForTabImage);
        mTabImage.setSelected(false);

        int padding = (int) getResources().getDimension(R.dimen.padding_3);
        mTabImage.setPadding(padding, 0, padding, 0);
        tabImageParam.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);

        tabImageContainer.addView(mTabImage, tabImageParam);
    }

    //向tab加文字
    private void addTabText(RelativeLayout tabImageContainer) {
        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER_HORIZONTAL;
        params.topMargin = (int) getResources().getDimension(R.dimen.tab_text_mar_top);
        addView(tabImageContainer, params);
        mTabText = new TextView(getContext());
        mTabText.setId(0);
        addView(mTabText, params);
    }

    //向tab加小红点
    private void addIndicatorView(RelativeLayout tabImageContainer) {
        int indicatorWidth = getResources().getDimensionPixelSize(R.dimen.indicator_width);
        RelativeLayout.LayoutParams tabIndicatorParam = new RelativeLayout.LayoutParams(indicatorWidth, indicatorWidth);
        //用于展示小红点／数字
        mIndicatorTextView = new TextView(getContext());
        mIndicatorTextView.setVisibility(View.INVISIBLE);
        mIndicatorTextView.setGravity(Gravity.CENTER);
        mIndicatorTextView.setTextColor(Color.WHITE);
        mIndicatorTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.indicator_text_size));
        tabIndicatorParam.addRule(RelativeLayout.ALIGN_RIGHT, R.id.idForTabImage);
        tabImageContainer.addView(mIndicatorTextView, tabIndicatorParam);

    }

    @Override
    public void setSelected(boolean selected) {
        super.setSelected(selected);
        mTabText.setSelected(selected);
        mTabImage.setSelected(selected);
    }

    @Override
    public boolean isSelected() {
        return mTabText.isSelected();
    }


    //显示小红点
    public void showNotifyNo(String number) {
        RelativeLayout.LayoutParams tabIndicatorParam = (RelativeLayout.LayoutParams) mIndicatorTextView.getLayoutParams();
        int indicatorWidth = getResources().getDimensionPixelSize(R.dimen.tab_notify_dot);
        if (!TextUtils.isEmpty(number) && !"0".equals(number)) {
            int totalNumber = Integer.parseInt(number);
            if (totalNumber > 0) {
                mIndicatorTextView.setText(number);
            } else {
                indicatorWidth = getResources().getDimensionPixelSize(R.dimen.indicator_samll_width);
                mIndicatorTextView.setText("");
            }

            tabIndicatorParam.width = indicatorWidth;
            tabIndicatorParam.height = indicatorWidth;
            mIndicatorTextView.setBackgroundResource(R.drawable.red_dot);
            mIndicatorTextView.setVisibility(View.VISIBLE);
        } else {
            hideNotifyView();
        }
    }

    //隐藏小红点
    public void hideNotifyView() {
        mIndicatorTextView.setVisibility(View.GONE);
    }


    public void setButtunImageRes(int buttunImageRes) {
        if (buttunImageRes != 0) {
            mTabImage.setImageResource(buttunImageRes);
        }
    }


    public void setButtonTextContent(String buttonTextContent) {
        if (buttonTextContent != null) {
            mTabText.setText(buttonTextContent);
        }
    }
}
