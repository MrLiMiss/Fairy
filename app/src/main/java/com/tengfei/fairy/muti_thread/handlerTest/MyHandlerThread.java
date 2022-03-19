package com.tengfei.fairy.muti_thread.handlerTest;

import android.os.HandlerThread;
import android.os.Looper;

/**
 * @ Description : 此类，多线程时无法保证线程安全   myHandlerThread.start() 为子线程。
 * @ Author 李腾飞
 * @ Time 2022/3/18   12:11 AM
 * @ Version :
 */
public class MyHandlerThread extends Thread{
    Looper looper;

    MyHandlerThread(String name){super(name);}

    @Override
    public void run(){   //代码还没执行
        super.run();
        Looper.prepare();
        looper=Looper.myLooper();
        Looper.loop();//开始死循环
    }

    public Looper getLooper(){//先执行null

        return looper;
    }

    @Override
    public synchronized void start(){super.start();}
}
