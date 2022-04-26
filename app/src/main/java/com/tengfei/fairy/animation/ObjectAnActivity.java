package com.tengfei.fairy.animation;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;
import android.widget.TextView;

import com.tengfei.fairy.R;
import com.tengfei.fairy.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ Description :属性动画相关
 * @ Author 李腾飞
 * @ Time 2022/4/25   4:43 PM
 * @ Version :
 */
public class ObjectAnActivity extends BaseActivity {
    @BindView(R.id.tv_property)
    public TextView mTvProperty;
    @Override
    protected int getContentLayout() {
        return R.layout.activity_obj_anin;
    }



    @OnClick({R.id.btn_obj_scale,R.id.btn_obj_rota,R.id.btn_obj_alph,R.id.btn_obj_trans})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_obj_scale://缩放
                animScale();
                break;
            case R.id.btn_obj_rota://旋转
                animRota();
            case R.id.btn_obj_alph://透明度
                animAlph();
                break;
            case R.id.btn_obj_trans:
                animTrans();
                break;
            default:
                break;

        }
    }

    /**
     * 属性动画位移变化
     */
    private void animTrans() {
        float curTranslationX = mTvProperty.getTranslationX();
        ObjectAnimator animator = ObjectAnimator.ofFloat(mTvProperty, "translationX", curTranslationX, -500f, curTranslationX);
        animator.setDuration(5000);
        animator.start();
    }

    /**
     * 属性动画 透明度
     */
    private void animAlph() {
        ObjectAnimator animator=ObjectAnimator.ofFloat(mTvProperty,"alpha", 1f, 0f, 1f);
        animator.setDuration(3000);
        animator.start();
    }

    /**
     * 属性动画旋转
     */
    private void animRota() {
        ObjectAnimator animator=ObjectAnimator.ofFloat(mTvProperty, "rotation",0f,360f);
        animator.setDuration(3000);
        animator.start();
    }

    /**
     * 属性动画缩放
     */
    private void animScale() {
        ObjectAnimator animator=ObjectAnimator.ofFloat(mTvProperty,"scaleY", 1f, 3f, 1f);
        animator.setDuration(3000);
        animator.setRepeatCount(2);
        animator.setRepeatMode(ValueAnimator.REVERSE);//倒放
        animator.start();
    }

    @Override
    protected void initGui() {

    }

    @Override
    protected void initAction() {

    }

    @Override
    protected void initData() {

    }
}
