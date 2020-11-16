package com.tengfei.fairy.mvp;

/**
 * @ Description :mvp webactivity 基类
 * @ Author 李腾飞
 * @ Time 2020-11-16   15:13
 * @ Version :
 */
public class BaseWebActivity extends BaseMvpActivity {

    @Override
    protected int getLayoutId() {
        return 0;
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
