package com.tengfei.fairy.animation;

import android.animation.AnimatorSet;
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



    @OnClick({R.id.btn_obj_scale,R.id.btn_obj_rota,R.id.btn_obj_alph,R.id.btn_obj_trans,R.id.tv_obj_set,R.id.tv_value_anim_senior})
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
            case R.id.btn_obj_trans://位移
                animTrans();
                break;
            case R.id.tv_obj_set://属性动画组合
                animSet();
            case R.id.tv_value_anim_senior:// ValueAnimator 高级用法
                valueAnimSerior();
                break;
            default:
                break;

        }
    }

    /**
     * ValueAnimator 高级用法
     */
    private void valueAnimSerior() {

    }

    /**
     * 属性动画组合
     *       after(Animator anim)   将现有动画插入到传入的动画之后执行
     *       after(long delay)   将现有动画延迟指定毫秒后执行
     *       before(Animator anim)   将现有动画插入到传入的动画之前执行
     *       with(Animator anim)   将现有动画和传入的动画同时执行
     *
     *       TextView先从屏幕外移动进屏幕，然后开始旋转360度，旋转的同时进行淡入淡出操作
     *
     */
    private void animSet() {
        ObjectAnimator moveIn = ObjectAnimator.ofFloat(mTvProperty, "translationX", -500f, 0f);
        ObjectAnimator rotate = ObjectAnimator.ofFloat(mTvProperty, "rotation", 0f, 360f);
        ObjectAnimator fadeInOut = ObjectAnimator.ofFloat(mTvProperty, "alpha", 1f, 0f, 1f);
        AnimatorSet animSet = new AnimatorSet();
        animSet.play(rotate).with(fadeInOut).after(moveIn);
        animSet.setDuration(5000);
        animSet.start();
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
