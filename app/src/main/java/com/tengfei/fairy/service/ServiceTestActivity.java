package com.tengfei.fairy.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tengfei.fairy.R;
import com.tengfei.fairy.base.BaseActivity;
import com.tengfei.fairy.utils.IntentUtils;
import com.tengfei.fairy.utils.Logs;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ Description :service 相关测试：
 * @ Author 李腾飞
 * @ Time 2021/7/9   16:51
 * @ Version :
 */
public class ServiceTestActivity extends BaseActivity {


    public static final String CTL_ACTION = "org.crazyit.action.CTL_ACTION";
    public static final String UPDATE_ACTION = "org.crazyit.action.UPDATE_ACTION";

    @BindView(R.id.btn_startmusic)
    Button btn_start_music;//开始音乐
    @BindView(R.id.btn_suspendmusic)
    Button btn_suspend_music;//暂停音乐

    String[] titleStrs = new String[]{"NotAngry", "卡农", "summertime"};
    String[] authorStrs = new String[]{"任舒瞳", "孙昊天", "熊同学"};

    //ox开头表示十六进制，0开头表示8进制
    public int status = 0x11;//定义音乐播放状态 0x11 未播放，0x12 正在播放，0x13 暂停播放

    MusicReceiver musicReceiver;

    @BindView(R.id.tv_music_name)
    TextView tv_music_name;
    @BindView(R.id.tv_music_singer)
    TextView tv_music_singer;

    public static String TAG = ServiceTestActivity.class.getSimpleName();
    private Intent intent;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_service;
    }

    @OnClick({R.id.btn_start_service, R.id.btn_end_service, R.id.btn_bind_service, R.id.btn_unbind_service, R.id.btn_start_intent_service, R.id.btn_end_intent_service, R.id.btn_startmusic, R.id.btn_suspendmusic, R.id.btn_nextmusic, R.id.btn_premusic})
    void click(View view) {
        intent = new Intent("org.crazyit.action.CTL_ACTION");
        switch (view.getId()) {
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
            case R.id.btn_start_intent_service://启动IntentService
                IntentUtils.startIntentService(this);
                break;
            case R.id.btn_end_intent_service://关闭IntentService
                IntentUtils.endIntentService(this);
                break;
            case R.id.btn_startmusic:
                Logs.d(TAG, "开始音乐");
                intent.putExtra("control", 1);
                // 发送广播，将被Service组件中的BroadcastReceiver接收到
                sendBroadcast(intent);
                break;
            case R.id.btn_suspendmusic:
                intent.putExtra("control", 2);
                sendBroadcast(intent);
                Logs.d(TAG, "暂停音乐");
                break;
            case R.id.btn_nextmusic:
                intent.putExtra("control", 3);
                sendBroadcast(intent);
                Logs.d(TAG, "下一首音乐");
                break;
            case R.id.btn_premusic:
                intent.putExtra("control", 4);
                sendBroadcast(intent);
                Logs.d(TAG, "上一首音乐");
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

        musicReceiver = new MusicReceiver();
        // 创建IntentFilter，动态注册Brocasteceiver  无需AndroidMainfest.xml 再次注册
        IntentFilter filter = new IntentFilter();
        // 指定BroadcastReceiver监听的Action
        filter.addAction(UPDATE_ACTION);
        // 注册BroadcastReceiver
        registerReceiver(musicReceiver, filter);

        Intent intent = new Intent(this, MusicService.class);
        // 启动后台Service
        startService(intent);


    }

    @Override
    protected void initData() {

    }


    /**
     * 广播接收器
     */
    public class MusicReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            // 获取Intent中的update消息，update代表播放状态
            int update = intent.getIntExtra("update", -1);
            // 获取Intent中的current消息，current代表当前正在播放的歌曲
            int current = intent.getIntExtra("current", -1);
            if (current >= 0) {
                tv_music_name.setText(titleStrs[current]);
                tv_music_singer.setText(authorStrs[current]);
            }
            switch (update) {
                case 0x11:
//                    play.setImageResource(R.drawable.play);
                    status = 0x11;
                    break;
                // 控制系统进入播放状态
                case 0x12:
                    // 播放状态下设置使用暂停图标
//                    play.setImageResource(R.drawable.pause);
                    // 设置当前状态
                    status = 0x12;
                    break;
                // 控制系统进入暂停状态
                case 0x13:
                    // 暂停状态下设置使用播放图标
//                    play.setImageResource(R.drawable.play);
                    // 设置当前状态
                    status = 0x13;
                    break;
            }

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        intent.putExtra("control", 5);
        sendBroadcast(intent);
        Logs.d(TAG, "处理service");
    }
}
