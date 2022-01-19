package com.tengfei.fairy.jetpack.lifecycle;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleRegistry;

import com.tengfei.fairy.R;
import com.tengfei.fairy.config.Constants;
import com.tengfei.fairy.utils.AppUtils;
import com.tengfei.fairy.utils.IntentUtils;
import com.tengfei.fairy.widget.LeakmLastSrvView;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

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

    public Unbinder unbinder;

    public  String TAG=this.getClass().getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle);
        bindViews(this);
        // ComponentActivity及其子类默认实现了LifecycleOwner 接口
        getLifecycle().addObserver(new ActivityWorkUtils());
        /**
         * 直接使用Activity 需要手动完成注册并进行添加
         *
         *LifecycleRegistry lifecycleRegistry=new LifecycleRegistry(this);
         *lifecycleRegistry.addObserver(new ActivityWorkUtils());
         */
    }

    @OnClick({R.id.tv_service})
    public void click(View view){
        switch (view.getId()){
            case R.id.tv_service:
                IntentUtils.startLifeCycleService(this);
                break;
            default:
                break;

        }
    }


    public void bindViews(Activity activity) {
        unbinder = ButterKnife.bind(activity);
    }

    public void unbindViews() {
        try {
            unbinder.unbind();
        } catch (Exception e) {
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindViews();
        //华为设备mLastSrvView引起的内存泄漏
        if (AppUtils.isDevice(Constants.Device.HUAWEI)) {
            LeakmLastSrvView.fixLeak(this);
        }
    }



}
