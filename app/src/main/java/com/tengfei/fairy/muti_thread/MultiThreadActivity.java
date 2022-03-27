package com.tengfei.fairy.muti_thread;

import android.view.View;

import com.tengfei.fairy.R;
import com.tengfei.fairy.mvp.BaseMvpActivity;
import com.tengfei.fairy.utils.IntentUtils;

import butterknife.OnClick;

/**
 * @ Description :多线程相关测试
 * @ Author 李腾飞
 * @ Time 12/6/21   4:06 PM
 * @ Version :
 */
public class MultiThreadActivity extends BaseMvpActivity<MultiThreadPresenter> {


    @OnClick({R.id.tv_intent_service,R.id.tv_handler_thread,R.id.tv_asyncTask,R.id.tv_Handler,R.id.tv_thread})
    void onClick(View view){
        switch (view.getId()){
            case R.id.tv_asyncTask://AsyncTask
                IntentUtils.toAsyncTask(this);
                break;
            case R.id.tv_Handler://handler
                IntentUtils.toHandlerActivity(this);
                break;
            case R.id.tv_intent_service://IntentService 测试
                IntentUtils.toIntentService(this);
                break;

        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_multi_thread;
    }

    @Override
    protected MultiThreadPresenter loadPresenter() {
        return new MultiThreadPresenter();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }
}
