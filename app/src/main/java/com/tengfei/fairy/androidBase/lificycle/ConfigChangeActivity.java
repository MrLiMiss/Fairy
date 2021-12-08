package com.tengfei.fairy.androidBase.lificycle;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.tengfei.fairy.R;

/**
 * @ Description :  onConfigurationChanged  测试横竖屏切换
 * 1、android:configChanges="keyboardHidden|orientation|screenSize"   切换横竖屏，调用onConfigurationChanged方法
 * 2、未设置，横屏切竖屏，竖屏切横屏，均需要先销毁本activity（onpause-》onstop-》osDestory，再重建oncreate-》onStart-》onresume）
 * @ Author 李腾飞
 * @ Time 2020/12/7   09:58
 * @ Version :
 */
public class ConfigChangeActivity extends AppCompatActivity {

    private static final String TAG = "onCongigChange";

    //Activity创建时调用
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configchange);
        Log.i(TAG,"执行了onCreate方法");
    }

    //Activity创建或者从后台重新回到前台时被调用
    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG,"执行了onStart方法");
    }

    //Activity从后台重新回到前台时被调用
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG,"执行了onRestart方法");
    }

    //Activity创建或者从被覆盖、后台重新回到前台时被调用
    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG,"执行了onResume方法");
    }

    //Activity被覆盖到下面或者锁屏时被调用
    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG,"执行了onPause方法");
    }

    //退出当前Activity或者跳转到新Activity时被调用
    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG,"执行了onStop方法");
    }

    //退出当前Activity时被调用,调用之后Activity就结束了
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG,"执行了onDestroy方法");
    }
    //当指定了android:configChanges="orientation"后,方向改变时onConfigurationChanged被调用,并且activity不再销毁重建
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        switch (newConfig.orientation) {
            case Configuration.ORIENTATION_PORTRAIT://竖屏
                Log.i(TAG,"竖屏");
                break;
            case Configuration.ORIENTATION_LANDSCAPE://横屏
                Log.i(TAG,"横屏");
            default:
                break;
        }
    }
}
