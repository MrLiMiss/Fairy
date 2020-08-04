package com.tengfei.fairy.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;

import com.tengfei.fairy.R;
import com.tengfei.fairy.utils.AppUtils;
import com.tengfei.fairy.utils.IntentUtils;

/**
 * @ Description :splash页面
 * @ Author 李腾飞
 * @ Time 2020-08-04   11:03
 * @ Version :
 */
public class SplashActivity extends Activity {
    protected View mRootView;
    protected Activity mActivity;

    @Override
    protected void onCreate(Bundle savedactivityState){
        super.onCreate(savedactivityState);
        this.mActivity=this;
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        mRootView = View.inflate(this, getLayoutId(), null);
        initView();
        initAnima();
    }

    private void initAnima() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                /**
                 *要执行的操作
                 */
                IntentUtils.toMainActivity(mActivity);
                mActivity.finish();
            }
        }, 3000);//3秒后执行Runnable中的run方法
    }

    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    protected void initView() {
        if (!isTaskRoot()
                && getIntent().hasCategory(Intent.CATEGORY_LAUNCHER)
                && getIntent().getAction() != null
                && getIntent().getAction().equals(Intent.ACTION_MAIN)) {

            finish();
            return;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            WindowManager.LayoutParams lp = getWindow().getAttributes();
            lp.layoutInDisplayCutoutMode
                    = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
            getWindow().setAttributes(lp);
        }
        AppUtils.hideNavigationBar(mActivity);


    }

}
