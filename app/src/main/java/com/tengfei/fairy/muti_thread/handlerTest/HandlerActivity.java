package com.tengfei.fairy.muti_thread.handlerTest;

import com.tengfei.fairy.R;
import com.tengfei.fairy.mvp.BaseMvpActivity;
import com.tengfei.fairy.mvp.BasePresenter;

/**
 * @ Description :  handler 相关测试
 * @ Author 李腾飞
 * @ Time 12/15/21   3:08 PM
 * @ Version :
 */
public class HandlerActivity extends BaseMvpActivity<BasePresenter> {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_handler;
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
