package com.tengfei.fairy.widget;

import android.view.View;

import com.tengfei.fairy.R;
import com.tengfei.fairy.mvp.BaseMvpActivity;
import com.tengfei.fairy.mvp.BasePresenter;
import com.tengfei.fairy.utils.IntentUtils;
import com.tengfei.fairy.utils.ToastTools;
import com.tengfei.fairy.widget.wrapLayout.WrapLayout;

import butterknife.BindView;
import butterknife.OnClick;

import static com.tengfei.fairy.application.FairyApplication.getContext;

/**
 * @ Description :自定义组件 widget
 * @ Author 李腾飞
 * @ Time 1/17/22   3:18 PM
 * @ Version :
 */
public class WidgetActivity extends BaseMvpActivity<BasePresenter> {
    @BindView(R.id.view_wraplayout)
    WrapLayout wrapLayout;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_weidget;
    }

    @OnClick({R.id.btn_wrapLayout,R.id.btn_my_view,R.id.btn_sign,R.id.btn_cropPicyure})
    void click(View view){
        switch (view.getId()){
            case R.id.btn_wrapLayout://自定义wrapLayout
                wrapLayout();
                break;
            case R.id.btn_my_view:
                IntentUtils.toMyViewActivity(getContext());
                break;
            case R.id.btn_sign:
                IntentUtils.toSignActivity(getContext());
                break;
            case R.id.btn_cropPicyure:
                break;
            default:
                break;
        }
    }

    private void wrapLayout() {
        String[] myData={
                "斗地主", "抓金花","德州扑克","四川麻将","桌球","划拳","摇色子"
        };
        wrapLayout.setData(myData,this,12,10,10,10,10,10,10,10,10);
        wrapLayout.setMarkClickListener(new WrapLayout.MarkClickListener() {
            @Override
            public void clickMark(int position) {
                ToastTools.showLong(WidgetActivity.this,myData[position]);
            }
        });
    }

    @Override
    protected BasePresenter loadPresenter() {
        return null;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }
}
