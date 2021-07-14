package com.tengfei.fairy.service;

import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.tengfei.fairy.R;
import com.tengfei.fairy.base.BaseActivity;
import com.tengfei.fairy.utils.IntentUtils;

import butterknife.OnClick;

/**
 * @ Description :service 相关测试：
 * @ Author 李腾飞
 * @ Time 2021/7/9   16:51
 * @ Version :
 */
public class ServiceTestActivity extends BaseActivity {
    public static String TAG=ServiceTestActivity.class.getSimpleName();
    @Override
    protected int getContentLayout() {
        return R.layout.activity_service;
    }

    @OnClick({R.id.btn_start_service,R.id.btn_end_service,R.id.btn_bind_service,R.id.btn_unbind_service,R.id.btn_start_intent_service,R.id.btn_end_intent_service})
    void click(View view){
       switch (view.getId()){
           case R.id.btn_start_service://启动service
               IntentUtils.startMusicService(this);
               break;
           case R.id.btn_end_service://关闭service
               IntentUtils.endMusicService(this);
               break;
           case R.id.btn_bind_service://绑定service
               IntentUtils.bindMusicService(this);
               break;
           case R.id.btn_unbind_service://解绑service
               IntentUtils.unBindMusicService(this);
               break;
           case R.id.btn_start_intent_service:
               IntentUtils.startIntentService(this);
               break;
           case R.id.btn_end_intent_service:
               IntentUtils.endIntentService(this);
               break;


           default:
               break;
       }
    }

    @Override
    protected void initGui() {
        Log.e(TAG, "ServiceTestActivity-----" + Thread.currentThread().getName() + "--" + Thread.currentThread().getId());
    }

    @Override
    protected void initAction() {

    }

    @Override
    protected void initData() {

    }
}
