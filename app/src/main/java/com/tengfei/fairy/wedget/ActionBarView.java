package com.tengfei.fairy.wedget;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.tengfei.fairy.R;
import com.tengfei.fairy.api.SingleClick;
import com.tengfei.fairy.utils.StringUtil;

import java.util.ArrayList;

/**
 * @ Description :
 * @ Author 李腾飞
 * @ Time 2020/12/22   15:20
 * @ Version :
 */
public class ActionBarView extends FrameLayout implements View.OnClickListener {
    public ArrayList<String> data = new ArrayList<>();
    private LinearLayout llGroup;
    private TextView tvTitle;
    private TextView tvRight;
    private ImageView ivBack;
    private ActionBarViewCallBack mCallBack;
    public int mSearchType;
    public int mCustomType = 2;

    public ActionBarView(Context context) {
        this(context, null);
    }

    public ActionBarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ActionBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ActionBarView);
        boolean hideSearch = typedArray.getBoolean(R.styleable.ActionBarView_hideSearch, false);
        String actionBarViewTitle = typedArray.getString(R.styleable.ActionBarView_titleStr);
        String actionBarViewRightTitle = typedArray.getString(R.styleable.ActionBarView_rightStr);
        boolean hideBack = typedArray.getBoolean(R.styleable.ActionBarView_hideBack, false);
        int rightStrColor = typedArray.getColor(R.styleable.ActionBarView_rightStrColor, ContextCompat.getColor(getContext(), R.color.colorBlack));
        typedArray.recycle();

        View actionBar = View.inflate(getContext(), R.layout.actionbar_layout, this);
        llGroup = actionBar.findViewById(R.id.llGroup);
        ivBack = actionBar.findViewById(R.id.ivBack);
        setBackStatus(hideBack);
        tvTitle = actionBar.findViewById(R.id.tvTitle);
        tvRight = actionBar.findViewById(R.id.tvRight);
        ImageView ivSearch = actionBar.findViewById(R.id.ivSearch);
        ivSearch.setVisibility(hideSearch ? GONE : VISIBLE);
        tvTitle.setText(actionBarViewTitle);
        if (StringUtil.isNotEmpty(actionBarViewRightTitle)) {
            tvRight.setText(actionBarViewRightTitle);
            tvRight.setVisibility(VISIBLE);
            tvRight.setTextColor(rightStrColor);
        }
        ivBack.setOnClickListener(this);
        ivSearch.setOnClickListener(this);
        tvTitle.setOnClickListener(this);
    }

    private void setBackStatus(boolean hideBack) {
        if(null != ivBack)
            ivBack.setVisibility(hideBack ? GONE : VISIBLE);
    }

    public void setTitle(String title) {
        tvTitle.setText(title);
    }

    public void setTitle(int resId) {
        tvTitle.setText(getResources().getText(resId));
    }

    public void setRight(String text) {
        if (null != tvRight) {
            tvRight.setText(text);
        }
    }

    public void setTvRightBg(int drawable) {
        if (null != tvRight) {
            tvRight.setBackgroundResource(drawable);
            tvRight.setPadding(getContext().getResources().getDimensionPixelSize(R.dimen.dp_10),getContext().getResources().getDimensionPixelSize(R.dimen.dp_4), getContext().getResources().getDimensionPixelSize(R.dimen.dp_10), getContext().getResources().getDimensionPixelSize(R.dimen.dp_4));
        }
    }

    public void setRightTitleFontSize(int size) {
        if (null != tvRight) {
            tvRight.setTextSize(TypedValue.COMPLEX_UNIT_SP, size);
        }
    }

    /**
     * 添加自定义icon在标题栏右边
     *
     * @param imgId   图片id
     * @param onClick 点击事件
     */
    public void addIconToRight(int imgId, View.OnClickListener onClick) {
        ImageView imageView = new ImageView(getContext());
        imageView.setImageResource(imgId);
        FrameLayout.LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        imageView.setPadding(getContext().getResources().getDimensionPixelOffset(R.dimen.spacing_normal), 0, 0, 0);
        imageView.setLayoutParams(params);
        imageView.setOnClickListener(onClick);
        llGroup.addView(imageView);
    }

    @Override
    @SingleClick(1000)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivBack:
                if (null == mCallBack) {
                    ((Activity) getContext()).finish();
                    return;
                }
                mCallBack.back();
                break;
            case R.id.ivSearch:
//                if (null == mCallBack) {
//                    IntentUtils.toSearchAct(getContext(), mSearchType, mCustomType);
//                    return;
//                }
//                mCallBack.search();
                break;
            case R.id.tvTitle:
//                IntentUtils.toDesAct((Activity) getContext(), data);

                break;
        }
    }

    public void setRightTextClickListener(OnClickListener listener) {
        if (null != tvRight && null != listener) {
            tvRight.setOnClickListener(listener);
        }
    }

    public void setCallBack(ActionBarViewCallBack callBack) {
        mCallBack = callBack;
    }

    public interface ActionBarViewCallBack {
        void back();

        void search();
    }

}
