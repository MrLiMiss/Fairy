package com.tengfei.fairy.muti_thread;

/**
 * @ Description : synchronized
 * 场景：多窗口卖票
 * @ Author 李腾飞
 * @ Time 2021/2/22   11:05
 * @ Version :
 */
public class SyncThreadTest {

    private int num=20;
    //加synchronized修饰的sell方法
    public synchronized void sell(){
        if(num==0){
            return;
        }
        num--;
        System.out.println(Thread.currentThread().getName()+"卖出一张票,剩余"+num+"张");
    }

    //未加synchronized修饰的sell方法
//    public void sell(){
//        if(num==0){
//            return;
//        }
//        num--;
//        System.out.println(Thread.currentThread().getName()+"卖出一张票,剩余"+num+"张");
//    }
    class window implements Runnable{

        @Override
        public void run() {
            // TODO Auto-generated method stub
            while(true){
                sell();
                try {
                    Thread.sleep(1000*2);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

    }
    public static void main(String[] args) {
        SyncThreadTest d = new SyncThreadTest();
        window w = d.new window();
        new Thread(w, "窗口1").start();
        new Thread(w, "窗口2").start();
        new Thread(w, "窗口3").start();
        new Thread(w, "窗口4").start();
    }

    }
