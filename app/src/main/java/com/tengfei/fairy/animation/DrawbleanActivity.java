package com.tengfei.fairy.animation;

import android.graphics.drawable.AnimationDrawable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.tengfei.fairy.R;
import com.tengfei.fairy.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ Description :帧动画测试
 * @ Author 李腾飞
 * @ Time 2022/4/24   4:19 PM
 * @ Version :
 */
public class DrawbleanActivity  extends BaseActivity {
    @BindView(R.id.img_show)
    public  ImageView img_show;
    private FrameView fView;
    private AnimationDrawable anim2 = null;

    private AnimationDrawable anim;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_drawblean;
    }


    @OnClick({R.id.btn_start,R.id.btn_stop,R.id.img_show})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_start:
                anim.start();
                break;
            case R.id.btn_stop:
                anim.stop();
                break;
        }

    }

    @Override
    protected void initGui() {
        anim = (AnimationDrawable) img_show.getBackground();
        startAnin();

    }

    /**
     * 手点哪里，在哪里显示动画
     */
    private void startAnin() {
        FrameLayout fly = findViewById(R.id.fl_ontouch);
        fView = new FrameView(this);
        fView.setBackgroundResource(R.drawable.anim_zhuan);
        fView.setVisibility(View.INVISIBLE);
        anim2= (AnimationDrawable) fView.getBackground();
        fView.setAnim(anim2);
        fly.addView(fView);
        fly.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //设置按下时才产生动画效果
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    anim2.stop();
                    float x = event.getX();
                    float y = event.getY();
                    fView.setLocation((int) y - 40,(int)x-20);  //View显示的位置
                    fView.setVisibility(View.VISIBLE);
                    anim2.start();    //开启动画
                }
                return false;
            }
        });
    }


    @Override
    protected void initAction() {

    }

    @Override
    protected void initData() {

    }
}
