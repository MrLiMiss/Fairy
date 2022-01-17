package com.tengfei.fairy.jetpack;

import com.tengfei.fairy.R;
import com.tengfei.fairy.mvp.BaseMvpActivity;
import com.tengfei.fairy.mvp.BasePresenter;

/**
 * @ Description :JetPack测试
 * @ Author 李腾飞
 * @ Time 1/17/22   9:56 AM
 * @ Version :
 */
public class JetPackActivity extends BaseMvpActivity<BasePresenter> {

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

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }
}
