package com.tengfei.fairy.jetpack;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.tengfei.fairy.R;
import com.tengfei.fairy.mvp.BaseMvpActivity;
import com.tengfei.fairy.mvp.BasePresenter;
import com.tengfei.fairy.utils.IntentUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ Description :JetPack测试
 * @ Author 李腾飞
 * @ Time 1/17/22   9:56 AM
 * @ Version :
 */
public class JetPackActivity extends BaseMvpActivity<BasePresenter> {
    @BindView(R.id.tv_lifecycles)
    TextView mTvLifeCycle;


    @OnClick({R.id.tv_lifecycles})
    void onClick(View view){
        switch (view.getId()){
            case R.id.tv_lifecycles://  jetpack 生命周期测试
                IntentUtils.toLifeCycleAct(mActivity);
                break;
            default:
                break;
        }
    }

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
