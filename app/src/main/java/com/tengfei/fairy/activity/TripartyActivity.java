package com.tengfei.fairy.activity;

import android.view.View;

import com.tengfei.fairy.R;
import com.tengfei.fairy.base.BaseActivity;
import com.tengfei.fairy.utils.IntentUtils;

import butterknife.OnClick;

import static com.tengfei.fairy.application.FairyApplication.getContext;

/**
 * @ Description :第三方框架相关测试Activity
 * @ Author 李腾飞
 * @ Time 2021/7/16   14:15
 * @ Version :
 */
public class TripartyActivity extends BaseActivity {
    @Override
    protected int getContentLayout() {
        return R.layout.activity_triparty;
    }

    @Override
    protected void initGui() {

    }

    @OnClick({R.id.btn_EventBus,R.id.btn_livedata,R.id.btn_jetpack})
    void click(View view){
        switch (view.getId()){
            case R.id.btn_EventBus://eventBus测试
                IntentUtils.toEventBusActivity(activity);
                break;
            case R.id.btn_livedata://liveData 测试
                IntentUtils.toLiveDataActivity(activity);
                break;
            case R.id.btn_jetpack://jetpack测试
                IntentUtils.toJetPackActivity(activity);
                break;
            default:
                break;
        }

    }

    @Override
    protected void initAction() {

    }

    @Override
    protected void initData() {

    }
}
