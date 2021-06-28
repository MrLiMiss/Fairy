package com.tengfei.fairy.base;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.tengfei.fairy.R;
import com.tengfei.fairy.utils.Logs;

import butterknife.ButterKnife;

/**
 * @ Description :基础 mvc activity类
 * @ Author 李腾飞
 * @ Time 2020-09-04   10:01
 * @ Version :
 */
public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {
    protected Activity activity;

    @Override
    protected void onCreate(Bundle savedactivityState) {
        super.onCreate(savedactivityState);
        if (getContentLayout() != 0) {
            setContentView(getContentLayout());
        }
        Logs.i("lifecycle-BaseActivity", "onCreate()");
        activity = this;
        ButterKnife.bind(this);
        initGui();
        initAction();
        initData();
    }

    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        if (res.getConfiguration().fontScale != 1) {
            Configuration newConfig = new Configuration();
            newConfig.setToDefaults();
            res.updateConfiguration(newConfig, res.getDisplayMetrics());
        }
        return res;
    }


    /*
     * 初始化布局
     */
    protected abstract int getContentLayout();


    /**
     * 初始化UI
     */
    protected abstract void initGui();

    /**
     * 初始化事件
     */
    protected abstract void initAction();

    /**
     * 初始化数据
     */
    protected abstract void initData();


    public void clearPopBackStack() {//清空回退栈
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.popBackStack(null,
                FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }


    /**
     * 根据资源id获取值
     *
     * @param resId
     * @return
     * @Description
     */
    protected String getResString(int resId) {
        return activity.getResources().getString(resId);
    }

    protected int getColorId(int resId) {
        return activity.getResources().getColor(resId);
    }


    private long lastClickTime;

    /**
     * 判断事件出发时间间隔是否超过预定值
     *
     * @return
     * @Description
     */
    public boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < 1000) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

    //重写startActivity()方法【添加activity跳转防重复点击】
    @Override
    public void startActivity(Intent intent) {
        // 防止连续点击
        if (isFastDoubleClick()) {
            Logs.d("BaseActivity", "startActivity() 重复调用");
            return;
        }
        super.startActivity(intent);
    }


    /**
     * 设置Activity全屏显示
     * 状态栏透明
     */
    public void initWindow() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(0x00000000);
        }
    }


    @Override
    public void onClick(View view) {
        // 防止连续点击
        if (isFastDoubleClick()) {
            Logs.d("BaseActivity", "startActivity() 重复调用");
            return;
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        Logs.i("lifecycle-BaseActivity", "onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Logs.i("lifecycle-BaseActivity", "onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Logs.i("lifecycle-BaseActivity", "onPause()");
    }


    @Override
    protected void onStop() {
        super.onStop();
        Logs.i("lifecycle-BaseActivity", "onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Logs.i("lifecycle-BaseActivity", "onDestroy()");
    }


}
