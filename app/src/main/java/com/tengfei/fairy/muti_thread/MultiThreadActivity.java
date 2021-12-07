package com.tengfei.fairy.muti_thread;

import com.tengfei.fairy.R;
import com.tengfei.fairy.mvp.BaseMvpActivity;

/**
 * @ Description :多线程相关测试
 * @ Author 李腾飞
 * @ Time 12/6/21   4:06 PM
 * @ Version :
 */
public class MultiThreadActivity extends BaseMvpActivity<MultiThreadPresenter> {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_multi_thread;
    }

    @Override
    protected MultiThreadPresenter loadPresenter() {
        return new MultiThreadPresenter();
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
