package com.tengfei.fairy.muti_thread.threadTest;

/**
 * @ Description :
 * @ Author 李腾飞
 * @ Time 1/29/22   4:43 PM
 * @ Version :
 */
public class ThreadTest {

    private static Object obj = new Object();

    public static void main(String[] args) {

        //测试 RunnableImplA wait()
        Thread t1 = new Thread(new RunnableImplA(obj));
        Thread t2 = new Thread(new RunnableImplA(obj));
        t1.start();
        t2.start();

//        //RunnableImplB notify()
//        Thread t3 = new Thread(new RunnableImplB(obj));
//        t3.start();


		//RunnableImplC notifyAll()
		Thread t4 = new Thread(new RunnableImplC(obj));
		t4.start();
    }

   static class RunnableImplA implements Runnable {

        private Object obj;

        public RunnableImplA(Object obj) {
            this.obj = obj;
        }

        public void run() {
            Thread thread=Thread.currentThread();
            String threadID=String.valueOf(thread.getId());
            System.out.println("RunnableImplA："+threadID+"---run on RunnableImplA");
            synchronized (obj) {
                System.out.println("RunnableImplA："+threadID+"---obj to wait on RunnableImplA");
                try {
                    obj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("RunnableImplA："+threadID+"---obj continue to run on RunnableImplA");
            }
        }
    }

    static class RunnableImplB implements Runnable {

        private Object obj;

        public RunnableImplB(Object obj) {
            this.obj = obj;
        }

        public void run() {
            Thread thread=Thread.currentThread();
            String threadID=String.valueOf(thread.getId());
            System.out.println("RunnableImplB："+threadID+"---run on RunnableImplB");
            System.out.println("睡眠3秒...");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (obj) {
                System.out.println("RunnableImplB："+threadID+"---notify obj on RunnableImplB");
                obj.notify();
            }
        }
    }

    static class RunnableImplC implements Runnable {

        private Object obj;

        public RunnableImplC(Object obj) {
            this.obj = obj;
        }

        public void run() {
            Thread thread=Thread.currentThread();
            String threadID=String.valueOf(thread.getId());
            System.out.println("RunnableImplC："+threadID+"---run on RunnableImplC");
            System.out.println("睡眠3秒...");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (obj) {
                System.out.println("RunnableImplC："+threadID+"---notifyAll obj on RunnableImplC");
                obj.notifyAll();
            }
        }
    }

}



