package com.tengfei.fairy.javaBase.volatileTest;

/**
 * @ Description :Volatile关键字 测试
 * volatile 用volatile修饰的变量，线程在每次使用变量的时候，都会读取变量修改后的最的值。但是不能保证原子性
 * @ Author 李腾飞
 * @ Time 12/29/21   2:00 PM
 * @ Version :
 */
public class VolatileTest {
    /**
     * count参数虽然使用volatile关键字修饰，仅能保证线程在每次使用变量的时候，都会读取变量修改后的最的值，不能保证原子操作
     */
    public volatile static int count = 0;

    public static void inc() {

        //这里延迟1毫秒，使得结果明显
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
        }

        count++;
    }

    public static void main(String[] args) {

        //同时启动1000个线程，去进行i++计算，看看实际结果

        for (int i = 0; i < 1000; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    VolatileTest.inc();
                }
            }).start();
        }

        //这里每次运行的值都有可能不同,可能为1000
        System.out.println("运行结果:VolatileTest.count=" + VolatileTest.count);
    }
}
