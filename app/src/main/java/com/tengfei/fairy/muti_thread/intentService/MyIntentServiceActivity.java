package com.tengfei.fairy.muti_thread.intentService;

import android.content.Intent;
import android.os.Bundle;

import com.tengfei.fairy.R;
import com.tengfei.fairy.base.BaseActivity;

/**
 * @ Description :测试IntentService
 * @ Author 李腾飞
 * @ Time 2022/3/27   9:44 PM
 * @ Version :
 */
public class MyIntentServiceActivity extends BaseActivity {
    @Override
    protected int getContentLayout() {
        return R.layout.activity_intentservice;
    }

    @Override
    protected void initGui() {

    }

    @Override
    protected void initAction() {

    }

    @Override
    protected void initData() {
        //同一服务只会开启一个工作线程
        // 在onHandleIntent函数里依次处理intent请求。
        Intent i = new Intent(this,MyIntentService.class);
       // Intent i = new Intent("cn.scu.finch") ;  //Android 5 （api 21）以后不允许隐式启动
        Bundle bundle = new Bundle();
        bundle.putString("taskName", "task1");
        i.putExtras(bundle);
        startService(i);

        Intent i2 = new Intent(this,MyIntentService.class);
        Bundle bundle2 = new Bundle();
        bundle2.putString("taskName", "task2");
        i2.putExtras(bundle2);
        startService(i2);
        startService(i); //多次启动

    }
}
