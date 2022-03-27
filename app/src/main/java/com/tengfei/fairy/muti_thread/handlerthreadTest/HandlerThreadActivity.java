package com.tengfei.fairy.muti_thread.handlerthreadTest;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.text.Html;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.tengfei.fairy.R;

/**
 * @ Description :HandlerThread 相关测试
 *   HandlerThread是Google帮我们封装好的，可以用来执行多个耗时操作，而不需要 多次开启线程，里面是采用Handler和Looper实现的
 * @ Author 李腾飞
 * @ Time 2020/12/10   11:14
 * @ Version :
 */
public class HandlerThreadActivity extends AppCompatActivity {
    private static final int MSG_UPDATE_INFO = 0x100;
    Handler mMainHandler = new Handler();
    private TextView mTv;
    private Handler mThreadHandler;
    private HandlerThread mHandlerThread;
    private boolean isUpdate = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handlerthread);
        mTv = (TextView) findViewById(R.id.tv);
        initHandlerThread();
    }

    private void initHandlerThread() {
        mHandlerThread = new HandlerThread("ltf-text");
        mHandlerThread.start();
        mThreadHandler = new Handler(mHandlerThread.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                checkForUpdate();
                if (isUpdate) {
                    mThreadHandler.sendEmptyMessage(MSG_UPDATE_INFO);
                }
            }
        };
    }

    /*** 模拟从服务器解析数据 */
    private void checkForUpdate() {
        try {//模拟耗时
            Thread.sleep(1200);
            mMainHandler.post(new Runnable() {
                @Override
                public void run() {
                    String result = "实时更新中，当前股票行情：<font color='red'>%d</font>";
                    result = String.format(result, (int) (Math.random() * 5000 + 1000));
                    mTv.setText(Html.fromHtml(result));
                }
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        isUpdate = true;
        super.onResume();
        mThreadHandler.sendEmptyMessage(MSG_UPDATE_INFO);
    }

    @Override
    protected void onPause() {
        super.onPause();
        isUpdate = false;
        mThreadHandler.removeMessages(MSG_UPDATE_INFO);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandlerThread.quit();
        mMainHandler.removeCallbacksAndMessages(null);
    }
}
