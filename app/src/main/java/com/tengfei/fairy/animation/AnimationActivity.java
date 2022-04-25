package com.tengfei.fairy.animation;

import android.view.View;
import android.widget.Button;

import com.tengfei.fairy.R;
import com.tengfei.fairy.mvp.BaseMvpActivity;
import com.tengfei.fairy.mvp.BasePresenter;
import com.tengfei.fairy.utils.IntentUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ Description :动画相关activity
 * @ Author 李腾飞
 * @ Time 2020-11-19   16:51
 * @ Version :
 */
public class AnimationActivity extends BaseMvpActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_animation;
    }

    @Override
    protected BasePresenter loadPresenter() {
        return new AnimationPresenter();
    }

    @OnClick({R.id.btn_drawablean,R.id.btn_rotatean,R.id.btn_alphaan})
    public void click(View view){
        switch (view.getId()){
            case R.id.btn_drawablean:
                //帧动画
                IntentUtils.toDrawblean(this);
                break;
            case R.id.btn_rotatean:
                //属性动画
            case R.id.btn_alphaan:
                //补间动画
            default:
                break;
        }
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
