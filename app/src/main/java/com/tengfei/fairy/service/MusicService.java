package com.tengfei.fairy.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.tengfei.fairy.activity.MainActivity;

import java.io.IOException;

/**
 * @ Description :自定义音乐播放器service
 * @ Author 李腾飞
 * @ Time 2021/7/9   17:16
 * @ Version :
 */
public class MusicService extends Service {

    public static final String TAG = MusicService.class.getSimpleName();


    public int current = 0;//当前播放的音乐

    AssetManager assetManager;//内部asset文件管理器

    String[] musics = new String[]{"任舒瞳_NotAngry.mp3", "熊同学_summertime.mp3", "孙昊天_卡农.mp3"};

    int status = 0x11; // 当前的状态，0x11代表没有播放；0x12代表正在播放；0x13代表暂停

    MediaPlayer mediaPlayer;

    MyReceiver myReceiver;

    private SimpleBinder mBinder;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate()");
        Log.e(TAG, "MusicService-----" + Thread.currentThread().getName() + "--" + Thread.currentThread().getId());
        mBinder = new SimpleBinder();

        assetManager = getAssets();
        myReceiver = new MyReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(ServiceTestActivity.CTL_ACTION);
        registerReceiver(myReceiver, filter);
        //创建MediaPlayer
        mediaPlayer = new MediaPlayer();
        //为MediaPlayer 播放完成时间绑定监听器
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                current++;
                if (current >= 3) {
                    current = 0;
                }
                //发送广播通知Activity更改文本框
                Intent sendIntent = new Intent(ServiceTestActivity.UPDATE_ACTION);
                sendIntent.putExtra("current", current);
                // 发送广播，将被Activity组件中的BroadcastReceiver接收到
                sendBroadcast(sendIntent);
                // 准备并播放音乐
                prepareAndPlay(musics[current]);

            }
        });
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {//service 启动时呗调用
        Log.d(TAG, "onStartCommand()");
        //模拟service ANR
//        try {
//            Thread.sleep(50000);
//            Log.d(TAG, "onStartCommand-耗时操作");
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        new Thread(new Runnable() {//service 处于主线程，但是可在service中起子线程，完成耗时操作。
            @Override
            public void run() {
                // 耗时任务
                Log.d(TAG, "onStartCommand-子线程");
            }
        }).start();
        Log.d(TAG, "onStartCommand-完成");
        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        //只有在即没有和任何Activity绑定又处于停止状态下的时候，才可以被摧毁。
        Log.d(TAG, "onDestroy");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {//应用程序通过IBinder 与service 组件  通信
        Log.d(TAG, "onBind");
//        测试bindservice 耗时操作
//        try {
//            Thread.sleep(50000);
//            Log.d(TAG, "onStartCommand-耗时操作");
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        if (mBinder != null) {
            return mBinder;
        }
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {//当service上绑定的所有客户端都断开连接时回调该方法
        Log.d(TAG, "onUnbind");
        return super.onUnbind(intent);
    }

    public class SimpleBinder extends Binder {

        public void doTask() {
            Log.d(TAG, "doTask");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    //子线程耗时操作。
                    Log.d(TAG, "SimpleBinder-子线程");

                }
            }).start();
        }
    }


    /**
     * 广播接收器接收 ServiceTestActivity 的广播
     */
    public class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(final Context context, Intent intent) {
            int control = intent.getIntExtra("control", -1);
            switch (control) {
                // 播放或暂停
                case 1:
                    // 原来处于没有播放状态
                    if (status == 0x11) {
                        // 准备并播放音乐
                        prepareAndPlay(musics[current]);
                        status = 0x12;
                    } else if (status == 0x12) {// 原来处于播放状态
                        return;
                    } else if (status == 0x13) {// 原来处于暂停状态
                        // 播放
                        mediaPlayer.start();
                        // 改变状态
                        status = 0x12;
                    }
                    break;
                // 停止声音
                case 2:
                    // 如果原来正在播放或暂停
                    if (status == 0x12 || status == 0x13) {
                        // 停止播放
                        mediaPlayer.stop();
                        status = 0x11;
                    }
                break;
                case 3:
                    current--;
                    if (current < 0)
                        current = 0;
                    prepareAndPlay(musics[current]);
                    break;
                case 4:
                    current++;
                    if (current > musics.length - 1)
                        current = 0;
                    prepareAndPlay(musics[current]);
                    break;
            }
            // 广播通知Activity更改图标、文本框
            Intent sendIntent = new Intent(ServiceTestActivity.UPDATE_ACTION);
            sendIntent.putExtra("update", status);
            sendIntent.putExtra("current", current);
            // 发送广播，将被Activity组件中的BroadcastReceiver接收到
            sendBroadcast(sendIntent);
        }
    }


    /**
     * 播放音乐
     *
     * @param music
     */
    private void prepareAndPlay(String music) {
        try {
            // 打开指定音乐文件
            AssetFileDescriptor afd = assetManager.openFd(music);
            mediaPlayer.reset();
            // 使用MediaPlayer加载指定的声音文件。
            mediaPlayer.setDataSource(afd.getFileDescriptor(),
                    afd.getStartOffset(), afd.getLength());
            // 准备声音
            mediaPlayer.prepare();
            // 播放
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
