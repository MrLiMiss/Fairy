package com.tengfei.fairy.event_conflict;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.tengfei.fairy.R;
import com.tengfei.fairy.anr.ANRActivity;
import com.tengfei.fairy.base.BaseActivity;
import com.tengfei.fairy.service.MusicService;
import com.tengfei.fairy.utils.IntentUtils;
import com.tengfei.fairy.utils.Logs;

import butterknife.BindView;
import butterknife.OnClick;

import static com.tengfei.fairy.application.FairyApplication.getContext;

/**
 * @ Description :
 * @ Author 李腾飞
 * @ Time 2022/3/16   10:42 AM
 * @ Version :
 */
public class EventActivity extends BaseActivity {
    @BindView(R.id.btn_conflict_out)
    Button mBtnConflictOut;

    @OnClick({R.id.btn_conflict_out,R.id.btn_btn_conflict_in})
    void click(View view){
        switch (view.getId()) {
            case R.id.btn_conflict_out://同方向 外部view处理
                IntentUtils.toOutEventActivity(getContext());
                break;
            case R.id.btn_btn_conflict_in:
                IntentUtils.toInEventActivity(getContext());
                break;
//            case R.id.btn_service_anr://服务超时ANR
//                Intent intent =new Intent(ANRActivity.this, MusicService.class);
//                intent.putExtra("testANR",true);
//                startService(intent);
//                break;
//            case R.id.btn_broadcast_anr://广播超时ANR
//                Logs.d(TAG,"广播超时");
//                break;
        }
    }

    @Override
    protected int getContentLayout() {
        return R.layout.activity_event;
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
