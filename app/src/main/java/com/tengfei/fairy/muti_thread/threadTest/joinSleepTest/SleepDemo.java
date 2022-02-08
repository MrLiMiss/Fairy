package com.tengfei.fairy.muti_thread.threadTest.joinSleepTest;

import java.util.Date;
import java.text.SimpleDateFormat;

import static com.tengfei.fairy.utils.DateUtils.getDateToString;

/**
 * @ Description :sleep() demo
 *
 * Thread.sleep(long) 方法是不释放对象锁的，并且使当前线程进入阻塞状态
 *
 * @ Author 李腾飞
 * @ Time 2/7/22   5:51 PM
 * @ Version :
 */
public class SleepDemo {

    static class ThreadA extends Thread{

        @Override
        public void run() {
            //线程A对自己加锁
            synchronized (this){
                System.out.println("------>"+"AAAAAAAA");
                System.out.println("------>"+"AAAAAAAA"+"  begin:"+getDateToString(System.currentTimeMillis()));

                System.out.println("------>"+"AAAAAAAA"+"  end:"+getDateToString(System.currentTimeMillis()));
            }
        }
    }

    static class ThreadB extends Thread{

        private ThreadA threadA;

        public ThreadB(ThreadA threadA) {
            this.threadA = threadA;
        }

        @Override
        public void run() {
            //线程B对线程A加锁
            synchronized (threadA){
                System.out.println("------>"+"BBBBBBBB"+"  begin:"+getDateToString(System.currentTimeMillis()));
                try {
//                    //1、sleep()不释放对象锁，
//                    Thread.sleep(4000);

                    //2、join（）释放对象锁
                    System.out.println("-------->before: "+threadA.isAlive());
                    threadA.join();
                    System.out.println("---------->after: "+threadA.isAlive());


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("------>"+"BBBBBBBB"+"  end:"+getDateToString(System.currentTimeMillis()));
            }
        }
    }

    public static void main(String[] args) {

        ThreadA threadA = new ThreadA();
        ThreadB threadB = new ThreadB(threadA);
        /**
         * 先执行的线程BBBBBBBBB的run方法，执行线程BBBBBBBB的run方法时，对线程AAAAAA对象加锁，
         * 只有一个线程能进入该方法执行，线程AAAAAAAAAA执行run方法时，因为线程AAAAAAAAAAA也对自己加锁，
         * 线程BBBBBBBBB还没有释放锁，所以线程AAAAAAAAAA不能执行其run方法中的同步块，
         * 需要等线程BBBBBBBBBB执行完成释放锁才能执行
         */
        threadB.start();
        threadA.start();
    }



}

