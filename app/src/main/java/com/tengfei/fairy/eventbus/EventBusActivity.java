package com.tengfei.fairy.eventbus;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.tengfei.fairy.R;
import com.tengfei.fairy.base.BaseActivity;
import com.tengfei.fairy.touch.TouchData;
import com.tengfei.fairy.utils.IntentUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;

/**
 * @ Description :MVC event相关测试
 * @ Author 李腾飞
 * @ Time 2020-11-24   14:27
 * @ Version :
 */
public class EventBusActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.tv_event)
    TextView tv_event;
    @BindView(R.id.btn_regester_event)
    Button btn_regester_event;
    @BindView(R.id.btn_unregester)
    Button btn_unregester;
    @BindView(R.id.btn_to_eventBus2)
    Button btn_to_eventBus2;
    @BindView(R.id.btn_testTouch)
    Button btn_testTouch;
    @BindView(R.id.tv_Logo)
    TextView tv_Logo;


    @Override
    protected int getContentLayout() {
        return R.layout.activity_eventbus;
    }

    @Override
    protected void initGui() {
        tv_Logo.setText("EventBusActivity");

    }

    @Override
    protected void initAction() {
        btn_regester_event.setOnClickListener(this);
        btn_unregester.setOnClickListener(this);
        btn_to_eventBus2.setOnClickListener(this);
        btn_testTouch.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    //订阅方法，当接收到事件的时候，会调用该方法
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(MessageEvent messageEvent) {
        Log.d("eventBus", "Main："+messageEvent.getMessage());
        tv_event.setText(messageEvent.getMessage());
        Toast.makeText(EventBusActivity.this, messageEvent.getMessage(), Toast.LENGTH_LONG).show();
    }


    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onPostEvent(MessageEvent messageEvent) {
        Log.d("eventBus", "POSTING："+messageEvent.getMessage());
       if(messageEvent.getMessageType().equals("POST")){
           tv_event.setText(messageEvent.getMessage());
           Toast.makeText(EventBusActivity.this, messageEvent.getMessage(), Toast.LENGTH_LONG).show();
       }

    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onBgEvent(MessageEvent messageEvent) {
        Log.d("eventBus", "BACKGROUND："+messageEvent.getMessage());
        if(messageEvent.getMessageType().equals("BACKGROUND")){
            tv_event.setText(messageEvent.getMessage());
            Toast.makeText(EventBusActivity.this, messageEvent.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void onStickyEvent(MessageEvent messageEvent) {
        Log.d("eventBus", "STICKY："+messageEvent.getMessage());
        if(messageEvent.getMessageType().equals("STICKY")){
            tv_event.setText(messageEvent.getMessage());
            Toast.makeText(EventBusActivity.this, messageEvent.getMessage(), Toast.LENGTH_LONG).show();
            EventBus.getDefault().removeStickyEvent(messageEvent);//使用完stickyEvent后移除
        }

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_to_eventBus2:
                IntentUtils.toEventBus2Activity(activity);
                break;
            case R.id.btn_regester_event://注册event事件
                EventBus.getDefault().register(this);
                break;
            case R.id.btn_unregester:
                EventBus.getDefault().unregister(this);
                break;
            case R.id.btn_testTouch:
                TouchData.trackView("eventbustrackViewdistinctId",false,"手机银行_理财","/mobile_bank_business");
                TouchData.trackClick("eventbusdistinctideventbus",false,"elemrnt-url","element-title","eventbus","eventbus-url");
                TouchData.trackEvent("eventbusMD5123123123start",false,"_AppStart");
            default:
                break;
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解除注册
        EventBus.getDefault().unregister(this);
    }
}
