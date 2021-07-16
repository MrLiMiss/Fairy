package com.tengfei.fairy.eventbus;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

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
    public  String TAG="EventBus2Activity";

    @BindView(R.id.iv_back)
    LinearLayout iv_back;
    @BindView(R.id.btn_sendMainMessage)
    Button btn_sendMainMessage;
    @BindView(R.id.btn_sendPostMessage)
    Button btn_sendPostMessage;
    @BindView(R.id.btn_sendbgMessage)
    Button btn_sendbgMessage;
    @BindView(R.id.btn_sendSticky)
    Button btn_sendSticky;
    @BindView(R.id.tv_Logo)
    TextView tv_Logo;
    @Override
    protected int getContentLayout() {
        return R.layout.activity_evnetbus2;
    }

    @Override
    protected void initGui() {
        tv_Logo.setText("EventBus2");
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initAction() {
        btn_sendMainMessage.setOnClickListener(this);
        btn_sendSticky.setOnClickListener(this);
        btn_sendbgMessage.setOnClickListener(this);

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
    switch (v.getId()){
        case R.id.btn_sendMainMessage : //发送消息
            sendMainMessage();//发送MAIN
            break;
        case R.id.btn_sendPostMessage:
            sendPostMessage();//发送POSTING
            break;
        case R.id.btn_sendbgMessage:
            sendBgMessage();
        case R.id.btn_sendSticky:
            sendSticky();//发送粘性事件
            break;

            default:
                break;
            
            
            
    }
    }

    /**
     * 发送background Event
     */
    private void sendBgMessage() {
        MessageEvent messageEvent=new MessageEvent("BgEvent from:"+TAG);
        messageEvent.setMessageType("BACKGROUND");
        EventBus.getDefault().post(messageEvent);
    }

    /**
     * 发送粘性事件
     */
    private void sendSticky() {
        MessageEvent messageEvent=new MessageEvent("StickyEvent from:"+TAG);
        messageEvent.setMessageType("STICKY");
        EventBus.getDefault().postSticky(messageEvent);
    }

    /**
     * 发送Posting Event
     */
    private void sendPostMessage() {
        MessageEvent messageEvent=new MessageEvent("PostingEvent from:"+TAG);
        messageEvent.setMessageType("POST");
        EventBus.getDefault().post(messageEvent);
    }

    /**
     * 发送Main Event
     */
    private void sendMainMessage() {
        MessageEvent messageEvent=new MessageEvent("MAINEvent from:"+TAG);
        messageEvent.setMessageType("Main");
        EventBus.getDefault().post(messageEvent);
    }
}
