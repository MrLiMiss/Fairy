package com.tengfei.fairy.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

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


    /*
     * 初始化布局
     */
    protected abstract int getContentLayout();

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
    protected void onStop(){
        super.onStop();
        Logs.i("lifecycle-BaseActivity", "onStop()");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Logs.i("lifecycle-BaseActivity", "onDestroy()");
    }

    /**
     * 初始化UI
     *
     * @Description
     * @author 刘国山 lgs@yitong.com.cn
     * @version 1.0 2013-6-18
     */
    protected abstract void initGui();

    /**
     * 初始化事件
     *
     * @Description
     * @author 刘国山 lgs@yitong.com.cn
     * @version 1.0 2013-6-18
     */
    protected abstract void initAction();

    /**
     * 初始化数据
     *
     * @Description
     * @author 刘国山 lgs@yitong.com.cn
     * @version 1.0 2013-6-18
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
     * @author 刘国山 lgs@yitong.com.cn
     * @version 1.0 2012-7-18
     */
    protected String getResString(int resId) {
        return activity.getResources().getString(resId);
    }

    protected int getColorId(int resId) {
        return activity.getResources().getColor(resId);
    }

    /**
     * 长提示
     *
     * @param msg
     * @Description
     * @author 刘国山 lgs@yitong.com.cn
     * @version 1.0 2012-7-18
     */
    protected void toastLong(String msg) {
        Toast.makeText(activity, msg, Toast.LENGTH_LONG).show();
    }

    /**
     * 长提示
     *
     * @param resId    文本资源ID
     * @Description
     * @author 刘国山 lgs@yitong.com.cn
     * @version 1.0 2012-7-19
     */
    protected void toastLong(int resId) {
        Toast.makeText(activity, getResString(resId), Toast.LENGTH_LONG).show();
    }

    /**
     * 短提示
     *
     * @param msg
     * @Description
     * @author 刘国山 lgs@yitong.com.cn
     * @version 1.0 2012-7-18
     */
    protected void toastShort(String msg) {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 短提示
     *
     * @param resId    文本资源ID
     * @Description
     * @author 刘国山 lgs@yitong.com.cn
     * @version 1.0 2012-7-19
     */
    protected void toastShort(int resId) {
        Toast.makeText(activity, getResString(resId), Toast.LENGTH_SHORT)
                .show();
    }

    private long lastClickTime;

    /**
     * 判断事件出发时间间隔是否超过预定值
     *
     * @return
     * @Description
     * @author 刘国山 lgs@yitong.com.cn
     * @version 1.0 2013年7月22日
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

    @Override
    public void onClick(View view) {
        // 防止连续点击
        if (isFastDoubleClick()) {
            Logs.d("BaseActivity", "startActivity() 重复调用");
            return;
        }
    }





}
