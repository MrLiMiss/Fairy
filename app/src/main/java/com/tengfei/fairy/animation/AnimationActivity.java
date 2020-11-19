package com.tengfei.fairy.animation;

import com.tengfei.fairy.R;
import com.tengfei.fairy.mvp.BaseMvpActivity;
import com.tengfei.fairy.mvp.BasePresenter;

/**
 * @ Description :动画相关activity
 * @ Author 李腾飞
 * @ Time 2020-11-19   16:51
 * @ Version :
 */
public class AnimationActivity<AnimationPreference> extends BaseMvpActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_animation;
    }

    @Override
    protected BasePresenter loadPresenter() {
        return new AnimationPresenter();
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
