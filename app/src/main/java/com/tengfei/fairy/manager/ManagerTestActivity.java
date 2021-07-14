package com.tengfei.fairy.manager;


import com.tengfei.fairy.R;
import com.tengfei.fairy.mvp.BaseMvpActivity;

/**
 * @ Description :系统manager 测试
 * @ Author 李腾飞
 * @ Time 2021/7/14   13:51
 * @ Version :
 */
public class ManagerTestActivity extends BaseMvpActivity<ManagerTestPresenter> implements IManagerView {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_manager;
    }

    @Override
    protected ManagerTestPresenter loadPresenter() {
        return null;//无数据请求  无需presenter处理
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
