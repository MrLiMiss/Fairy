package com.tengfei.fairy.muti_thread.threadTest.joinSleepWaitTest;

/**
 * @ Description :
 * join() 方法使得调用该方法的那段代码所在的线程（此处为主线程）暂时阻塞
 * 当无join（）时，主线程先执行完则不等待子线程执行完，（此处为主线程）已经结束。
 * @ Author 李腾飞
 * @ Time 2/7/22   5:29 PM
 * @ Version :
 */
public class JoinDemo {
    private static Thread thread1=new Thread(new Runnable() {
        @Override
        public void run() {
            System.out.println("子线程1在执行----------1111111111");
        }
    });

    private static Thread thread2 = new Thread(new Runnable() {
        @Override
        public void run() {
            System.out.println("子线程2在执行----------2222222222");
        }
    });

    /**
     * 测试 join（）方法效果
     * @param args
     * @throws InterruptedException
     */
    public static void main0(String[] args) throws InterruptedException {
        //主线程
        System.out.println("---------主线程------"+Thread.currentThread().getName());

//        1、无join（）,主线程执行完毕不等子线程直接结束
        System.out.println("子线程执行");
        //--------子线程执行
        thread1.start();
        thread2.start();
        //主线程希望等待子线程执行完再执行
        System.out.println("主线程还在执行");

    }


    /**
     * join() 方法使得调用该方法的那段代码所在的线程暂时阻塞
     * @param args
     * @throws InterruptedException
     */
    public static void main1(String[] args) throws InterruptedException {
        //主线程
        System.out.println("---------主线程------"+Thread.currentThread().getName());

        System.out.println("子线程执行");
        //--------子线程执行
        thread1.start();
        thread1.join();
        thread2.start();
        thread2.join();
        //主线程希望等待子线程执行完再执行
        System.out.println("主线程还在执行");
    }


    /**
     * @param args join（）：效果原理
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        //主线程
        System.out.println("主线程状态---------->"+Thread.currentThread().getState());
        System.out.println("---------主线程------"+Thread.currentThread().getName());
        System.out.println("主线程状态---------->"+Thread.currentThread().getState());
        System.out.println("子线程执行");
        //--------子线程执行
        thread1.start();
        System.out.println(Thread.currentThread().getName()+"线程状态---------->"+Thread.currentThread().getState());
        System.out.println("线程111111111状态-----------》"+thread1.getState());
        thread1.join();
        System.out.println("线程111111111状态-----------》"+thread1.getState());
        System.out.println(Thread.currentThread().getName()+"线程状态---------->"+Thread.currentThread().getState());
        thread2.start();
        System.out.println("线程222222222状态-----------》"+thread2.getState());
        thread2.join();
        System.out.println("线程222222222状态-----------》"+thread2.getState());
        System.out.println(Thread.currentThread().getName()+"线程状态---------->"+Thread.currentThread().getState());
        //主线程希望等待子线程执行完再执行
        System.out.println("主线程还在执行");
        System.out.println(Thread.currentThread().getName()+"线程状态---------->"+Thread.currentThread().getState());



        /**
         * 主线程状态---------->RUNNABLE
         * ---------主线程------main
         * 主线程状态---------->RUNNABLE
         * 子线程执行
         * main线程状态---------->RUNNABLE
         * 线程111111111状态-----------》RUNNABLE
         * 子线1程在执行----------1111111111
         * 线程111111111状态-----------》TERMINATED
         * main线程状态---------->RUNNABLE
         * 线程222222222状态-----------》RUNNABLE
         * 子线程2在执行----------2222222222
         * 线程222222222状态-----------》TERMINATED
         * main线程状态---------->RUNNABLE
         * 主线程还在执行
         * main线程状态---------->RUNNABLE
         */
    }



}
