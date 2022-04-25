package com.tengfei.fairy.animation;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.tengfei.fairy.R;
import com.tengfei.fairy.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ Description :补间动画相关：
 *    补间动画常用四种：
 *    AlphaAnimation：透明度渐变效果，ScaleAnimation：缩放渐变效果，
 *    TranslateAnimation：位移渐变效果，RotateAnimation：旋转渐变效果，AnimationSet：组合渐变
 *
 *     public TranslateAnimation(float fromXDelta, float toXDelta, float fromYDelta, float toYDelta) {}
 *     public TranslateAnimation(int fromXType, float fromXValue, int toXType, float toXValue,int fromYType, float fromYValue, int toYType, float toYValue) {}
 *
 *     RELATIVE_TO_SELF（相对于自身）和RELATIVE_TO_PARENT（相对于父布局），
 *     如果设置这个，pivotx,pivotY的值就应该是0-1的浮点数，这里分别对应xml中的%（自身）和%p（父布局）
 *
 *
 * @ Author 李腾飞
 * @ Time 2022/4/25   3:07 PM
 * @ Version :
 */
public class TweenActivity extends BaseActivity {
    @BindView(R.id.img_show)
    ImageView img_show;
    private Animation animation = null;
    private LinearLayout start_ctrl;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_tween;
    }


    @OnClick({R.id.btn_alpha,R.id.btn_scale,R.id.btn_tran,R.id.btn_rotate,R.id.btn_set})
    public void onClick(View view){
        switch (view.getId()){
            case  R.id.btn_alpha:
                //透明度变化
                animation = AnimationUtils.loadAnimation(this,
                        R.anim.anim_alpha);
                img_show.startAnimation(animation);
                break;
            case R.id.btn_scale:
                //缩放变化
                animation = AnimationUtils.loadAnimation(this,
                        R.anim.anim_scale);
                img_show.startAnimation(animation);
                break;
            case R.id.btn_tran:
                //位移变化
                animation = AnimationUtils.loadAnimation(this,
                        R.anim.anim_translate);
                img_show.startAnimation(animation);
                break;
            case R.id.btn_rotate:
                //旋转变化
                animation = AnimationUtils.loadAnimation(this,
                        R.anim.anim_rotate);
                img_show.startAnimation(animation);
                break;
            case R.id.btn_set:
                //组合变化
                animation = AnimationUtils.loadAnimation(this,
                        R.anim.anim_set);
                img_show.startAnimation(animation);
                break;
            default:
                break;


        }
    }

    @Override
    protected void initGui() {
        start_ctrl = (LinearLayout) findViewById(R.id.start_ctrl);
        //设置动画，从自身位置的最下端向上滑动了自身的高度，持续时间为500ms
        final TranslateAnimation ctrlAnimation = new TranslateAnimation(
                TranslateAnimation.RELATIVE_TO_SELF, 0, TranslateAnimation.RELATIVE_TO_SELF, 0,
                TranslateAnimation.RELATIVE_TO_SELF, 1, TranslateAnimation.RELATIVE_TO_SELF, 0);
        ctrlAnimation.setDuration(500l);     //设置动画的过渡时间
        start_ctrl.postDelayed(new Runnable() {
            @Override
            public void run() {
                start_ctrl.setVisibility(View.VISIBLE);
                start_ctrl.startAnimation(ctrlAnimation);
            }
        }, 2000);
    }

    @Override
    protected void initAction() {

    }

    @Override
    protected void initData() {

    }
}
