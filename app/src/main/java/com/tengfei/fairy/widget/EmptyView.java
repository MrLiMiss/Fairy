package com.tengfei.fairy.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.IntDef;

import com.tengfei.fairy.R;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author xianmei.li
 * @date 2019/11/01
 * @description 空页面，可用于Recyclerview
 */
public class EmptyView extends LinearLayout {

    /*网络连接失败*/
    public static final int ERROR_NO_HTTP = 1;
    /*暂无数据*/
    public static final int ERROR_NO_DATA = 2;
    /*无搜索结果*/
    public static final int ERROR_NO_SEARCH = 3;
    /*没有客户*/
    public static final int ERROR_NO_CUSTOM_ALL = 4;
    /*没有客户需补登*/
    public static final int ERROR_NO_CUSTOM_ALL_NEED = 5;
    /*没有存量业务*/
    public static final int ERROR_NO_STOCK = 6;
    /*没有还款计划*/
    public static final int ERROR_NO_REPAY_PLAN = 7;

    //意愿单未处理
    public static final int ERROR_NO_WISH_UNHANDLE = 8;
    //意愿单已处理
    public static final int ERROR_NO_WISH_HANDLED = 9;
    //意愿单已拒绝
    public static final int ERROR_NO_WISH_REFUSE = 10;

    @Target({
            ElementType.PARAMETER,
            ElementType.FIELD,
            ElementType.METHOD,
    }) //表示注解作用范围，参数注解，成员注解，方法注解
    @Retention(RetentionPolicy.SOURCE) //表示注解所存活的时间,在运行时,而不会存在 .class 文件中

    @IntDef({ERROR_NO_HTTP, ERROR_NO_DATA, ERROR_NO_SEARCH, ERROR_NO_CUSTOM_ALL, ERROR_NO_CUSTOM_ALL_NEED,})
    private @interface ERROR {
    }

    @BindView(R.id.empty_root)
    LinearLayout emptyRoot;
    @BindView(R.id.tv_hint)
    TextView mTvHint;
    @BindView(R.id.tv_refresh)
    TextView mBtnRefresh;
    @BindView(R.id.tv_hint2)
    TextView mTvHint2;
    private ClickCallBack clickCallBack;

    public EmptyView(Context context) {
        this(context, null);
    }

    public EmptyView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EmptyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.view_empty, this, true);
        ButterKnife.bind(this);
    }


    public void setHint(@ERROR int behavior) {
        if (null == mTvHint) {
            return;
        }
        switch (behavior) {
            case ERROR_NO_DATA:
                setImg(R.drawable.ic_no_data);
                setHintText(getContext().getString(R.string.http_no_data));
                setButtonVisible(View.GONE);
                break;
            case ERROR_NO_HTTP:
                setImg(R.drawable.ic_http_error);
                setHintText(getContext().getString(R.string.http_connect_fail));
                setButtonVisible(View.VISIBLE);
                break;
            case ERROR_NO_SEARCH:
                setImg(R.drawable.ic_no_search);
                setHintText(getContext().getString(R.string.search_no_data));
                setHintText2(getContext().getString(R.string.search_change_key));
                break;
            case ERROR_NO_CUSTOM_ALL:
                setImg(R.drawable.ic_no_data);
                setHintText(getContext().getString(R.string.custom_no_data_1));
                setButtonVisible(View.GONE);
                break;
            case ERROR_NO_CUSTOM_ALL_NEED:
                setImg(R.drawable.ic_no_data);
                setHintText(getContext().getString(R.string.custom_no_data_2));
                setButtonVisible(View.GONE);
                break;
            case ERROR_NO_STOCK:
                setImg(R.drawable.ic_no_data);
                setHintText("暂无存量数据");
                setButtonVisible(View.GONE);
                break;
            case ERROR_NO_REPAY_PLAN:
                setImg(R.drawable.ic_no_data);
                setHintText("暂无还款计划");
                setButtonVisible(View.GONE);
                break;
            case ERROR_NO_WISH_UNHANDLE:
                setImg(R.drawable.ic_wishlist_empty);
                setHintText("暂无未受理任务");
                setButtonVisible(View.GONE);
                break;
            case ERROR_NO_WISH_HANDLED:
                setImg(R.drawable.ic_wishlist_empty);
                setHintText("暂无已受理任务");
                setButtonVisible(View.GONE);
                break;
            case ERROR_NO_WISH_REFUSE:
                setImg(R.drawable.ic_wishlist_empty);
                setHintText("暂无已拒绝任务");
                setButtonVisible(View.GONE);
                break;
            default:
                break;
        }
    }

    public void setHintText(CharSequence str) {
        if (null != mTvHint) {
            mTvHint.setText(str);
        }
    }

    public void setHintText2(CharSequence str) {
        if (null != mTvHint2) {
            mTvHint2.setText(str);
        }
    }

    public void setImg(int imgId) {
        if (null != mTvHint) {
            mTvHint.setCompoundDrawablesRelativeWithIntrinsicBounds(0, imgId, 0, 0);
        }
    }

    @OnClick({R.id.tv_refresh})
    void click(View v) {
        switch (v.getId()) {
            case R.id.tv_refresh:
                if (null != clickCallBack) {
                    clickCallBack.onRefreshBtnClick();
                }
                break;
            default:
                break;
        }
    }

    public void setButtonVisible(int visible) {
        if (null != mBtnRefresh) {
            mBtnRefresh.setVisibility(visible);
        }
    }

    public void setClickCallBack(ClickCallBack clickCallBack) {
        this.clickCallBack = clickCallBack;
        setButtonVisible(View.VISIBLE);
    }

    public interface ClickCallBack {
        void onRefreshBtnClick();
    }


    /**
     * 设置gravity，和padding值
     */
    public void setGravity(int gravity) {
        emptyRoot.setGravity(gravity);
    }

    public void setPadding(int left, int top, int right, int bottom) {
        emptyRoot.setPadding(left, top, right, bottom);
    }

}
