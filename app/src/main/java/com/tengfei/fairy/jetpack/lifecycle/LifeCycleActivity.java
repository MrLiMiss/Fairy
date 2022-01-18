package com.tengfei.fairy.jetpack.lifecycle;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.tengfei.fairy.R;

/**
 * @ Description : 测试jetpack lifecyce 相关
 * 在Activity中，只要使用的Activity是ComponentActivity及其子类的话，
 * 系统就已经默认帮你实现了LifecycleOwner 接口，可以直接拿到Lifecycle的实例
 *
 * LifeCycleActivity 完成观察生命周期设置
 * @ Author 李腾飞
 * @ Time 1/17/22   5:53 PM
 * @ Version :
 */
public class LifeCycleActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle);
        getLifecycle().addObserver(new WorkUtils());
    }
}
