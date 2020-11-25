package com.tengfei.fairy.eventbus;

import android.view.View;
import android.widget.Button;

import com.tengfei.fairy.R;
import com.tengfei.fairy.base.BaseActivity;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;

/**
 * @ Description :
 * @ Author 李腾飞
 * @ Time 2020-11-24   16:28
 * @ Version :
 */
public class EventBus2Activity extends BaseActivity implements View.OnClickListener{
    @BindView(R.id.btn_sendMessage)
    Button btn_sendMessage;
    @Override
    protected int getContentLayout() {
        return R.layout.activity_evnetbus2;
    }

    @Override
    protected void initGui() {

    }

    @Override
    protected void initAction() {
        btn_sendMessage.setOnClickListener(this);

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
    switch (v.getId()){
        case R.id.btn_sendMessage : //发送消息
            sendMessage();
            break;
            default:
                break;
            
            
            
    }
    }

    /**
     * 发送事件
     */
    private void sendMessage() {
        EventBus.getDefault().post(new MessageEvent("Hello !....."));
    }
}
