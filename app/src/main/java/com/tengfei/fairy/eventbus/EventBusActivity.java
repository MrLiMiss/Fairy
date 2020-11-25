package com.tengfei.fairy.eventbus;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.tengfei.fairy.R;
import com.tengfei.fairy.activity.MainActivity;
import com.tengfei.fairy.base.BaseActivity;
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
    @BindView(R.id.btn_to_eventBus2)
    Button btn_to_eventBus2;


    @Override
    protected int getContentLayout() {
        return R.layout.activity_eventbus;
    }

    @Override
    protected void initGui() {

    }

    @Override
    protected void initAction() {
        btn_regester_event.setOnClickListener(this);
        btn_to_eventBus2.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    //订阅方法，当接收到事件的时候，会调用该方法
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(MessageEvent messageEvent) {
        Log.d("cylog", "receive it");
        tv_event.setText(messageEvent.getMessage());
        Toast.makeText(EventBusActivity.this, messageEvent.getMessage(), Toast.LENGTH_LONG).show();
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
