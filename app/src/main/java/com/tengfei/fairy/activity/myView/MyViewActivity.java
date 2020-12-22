package com.tengfei.fairy.activity.myView;

import com.tengfei.fairy.R;
import com.tengfei.fairy.mvp.BaseMvpActivity;
import com.tengfei.fairy.wedget.DrawView;

import butterknife.BindView;

/**
 * @ Description :自定义View activity
 * @ Author 李腾飞
 * @ Time 2020/12/22   14:07
 * @ Version :
 */
public class MyViewActivity extends BaseMvpActivity<MyViewPresenter> implements IMyViewView {
    @BindView(R.id.view_drawView)
    DrawView drawView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_myview;
    }

    @Override
    protected MyViewPresenter loadPresenter() {
        return new MyViewPresenter();
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
