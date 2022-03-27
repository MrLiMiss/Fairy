package com.tengfei.fairy.muti_thread.intentService;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.tengfei.fairy.utils.Logs;

/**
 * @ Description :IntentService作用：处理异步请求，实现多线程。
 *       若启动IntentService多次，那么每个耗时操作则以队列的方式在
 *      IntentService的onHandleIntent回调方法中依次执行，执行完自动结束。
 *
 * IntentService本质是采用Handler & HandlerThread方式：
 *    1. 通过HandlerThread单独开启一个名为IntentService的线程 IntentService详解 557
 *    2. 创建一个名叫ServiceHandler的内部Handler
 *    3. 把内部Handler与HandlerThread所对应的子线程进行绑定
 *    4. 通过onStartCommand()传递给服务intent，依次插入到工作队列中，并逐个发 送给onHandleIntent()
 *    5. 通过onHandleIntent()来依次处理所有Intent请求对象所对应的任务 因此我们通过复写方法onHandleIntent()，再在里面根据Intent的不同进行不同的线 程操作就可以了
 *
 *
 * @ Author 李腾飞
 * @ Time 12/6/21   1:14 PM
 * @ Version :
 */
public class MyIntentService extends IntentService {


    public MyIntentService() {
        //调用父类的构造函数 //构造函数参数=工作线程的名字
        super("myIntentService");
    }

    /*复写onHandleIntent()方法*/ //实现耗时任务的操作
    @Override
    protected void onHandleIntent(Intent intent) {
        //根据Intent的不同进行不同的事务处理
        String taskName = intent.getExtras().getString("taskName");
        switch (taskName) {
            case "task1":
                Logs.d("myIntentService", "do task1");
                break;
            case "task2":
                Logs.d("myIntentService", "do task2");
                break;
            default:
                break;
        }
    }

    @Override
    public void onCreate() {
        Logs.d("myIntentService", "onCreate");
        super.onCreate();
    }

    /*复写onStartCommand()方法*/
    //默认实现将请求的Intent添加到工作队列里
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Logs.d("myIntentService", "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Logs.d("myIntentService", "onDestroy");
        super.onDestroy();
    }
}
