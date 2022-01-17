package com.tengfei.fairy.jetpack;

import com.tengfei.fairy.R;
import com.tengfei.fairy.mvp.BaseMvpActivity;
import com.tengfei.fairy.mvp.BasePresenter;
import com.tengfei.fairy.utils.ToastTools;
import com.tengfei.fairy.widget.wrapLayout.WrapLayout;

import butterknife.BindView;

/**
 * @ Description :JetPack测试
 * @ Author 李腾飞
 * @ Time 1/17/22   9:56 AM
 * @ Version :
 */
public class JetPackActivity extends BaseMvpActivity<BasePresenter> {
    @BindView(R.id.act_wrap)
    WrapLayout wrapLayout;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_jetpack;
    }

    @Override
    protected BasePresenter loadPresenter() {
        return new JetPackPresenter();
    }

    @Override
    protected void initView() {
        String[] myData={
                "斗地主", "抓金花","德州扑克","四川麻将","桌球","划拳","摇色子"
        };
        wrapLayout.setData(myData,this,12,10,10,10,10,10,10,10,10);
        wrapLayout.setMarkClickListener(new WrapLayout.MarkClickListener() {
            @Override
            public void clickMark(int position) {
                ToastTools.showLong(JetPackActivity.this,myData[position]);
            }
        });

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }
}
