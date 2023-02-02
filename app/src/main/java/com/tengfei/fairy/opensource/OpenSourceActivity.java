package com.tengfei.fairy.opensource;

import com.tengfei.fairy.R;
import com.tengfei.fairy.mvp.BaseMvpActivity;

/**
 * @ Description :开源组件页面
 * @ Author 李腾飞
 * @ Time 2023/2/2   5:16 PM
 * @ Version :
 */
public class OpenSourceActivity extends BaseMvpActivity<OpenSourcePresenter> implements IOpenSourceView {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_opensource;
    }

    @Override
    protected OpenSourcePresenter loadPresenter() {
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
