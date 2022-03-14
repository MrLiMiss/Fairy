package com.tengfei.fairy.anr;

import android.content.Intent;
import android.os.Build;
import android.view.View;
import android.widget.Button;

import androidx.annotation.RequiresApi;

import com.tengfei.fairy.R;
import com.tengfei.fairy.base.BaseActivity;
import com.tengfei.fairy.oom.singleIntense.SecondOOMActivity;
import com.tengfei.fairy.oom.singleIntense.SingleOOMActivity;
import com.tengfei.fairy.service.MusicService;
import com.tengfei.fairy.utils.IntentUtils;
import com.tengfei.fairy.utils.Logs;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ Description :ANR 相关测试
 * @ Author 李腾飞
 * @ Time 2022/3/14   9:10 AM
 * @ Version :
 */
public class ANRActivity extends BaseActivity  {
    public static String TAG=ANRActivity.class.getClass().getSimpleName();

    @BindView(R.id.btn_inputEvent_anr)
    Button mBtn_inputEvent;
    @BindView(R.id.btn_service_anr)
    Button mBtn_service;
    @BindView(R.id.btn_broadcast_anr)
    Button mBtn_broadcast;
    @BindView(R.id.btn_contentprovider_anr)
    Button mBtn_contentprovider;




    @Override
    protected int getContentLayout() {
        return R.layout.activity_anr;
    }

    @OnClick({R.id.btn_inputEvent_anr,R.id.btn_service_anr,R.id.btn_broadcast_anr,R.id.btn_contentprovider_anr})
    void click(View view) {
        switch (view.getId()) {
            case R.id.btn_inputEvent_anr://输入响应ANR
                try {
                    Thread.sleep(60000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                break;
            case R.id.btn_service_anr://服务超时ANR
                Intent intent =new Intent(ANRActivity.this, MusicService.class);
                intent.putExtra("testANR",true);
                startService(intent);
                break;
            case R.id.btn_broadcast_anr://广播超时ANR
                Logs.d(TAG,"广播超时");
                break;
        }
    }

    @Override
    protected void initGui() {

    }

    @Override
    protected void initAction() {

    }

    @Override
    protected void initData() {

    }
}
