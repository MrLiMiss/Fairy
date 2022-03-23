package com.tengfei.fairy.designPattern.productConsumer;

import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;

/**
 * @ Description : 多生产者  多消费者 ，生产消费者模式（单面包,生产一个，消费一个）
 * @ Author 李腾飞
 * @ Time 2022/3/13   4:13 PM
 * @ Version :
 */

public class ProductConsume_3 {
    public static void main(String[] args) {
        //1.创建资源对象
        Bread b = new Bread();
        //2.创建生产者和消费者对象
        Producer pro = new Producer(b);
        Consumer con = new Consumer(b);
        //3.创建线程对象
        Thread pro_t1 = new Thread(pro); //生产线程1
        Thread pro_t2 = new Thread(pro); //生产线程2
        Thread con_t1 = new Thread(con); //消费线程1
        Thread con_t2 = new Thread(con); //消费线程2
        pro_t1.start();
        pro_t2.start();
        con_t1.start();
        con_t2.start();
    }

    /**
     * 面包描述
     */
    static class Bread {
        public String name;
        public int count = 1;
        public boolean flag = false;
    }

    //描述生产者
    static class Producer implements Runnable {
        private Bread b;

        Producer(Bread b) {
            this.b = b;
        }

        public void produce(String name) {
            b.name = name + b.count;
            b.count++;
        }

        public void run() {
            while (true) {
                synchronized (Bread.class) {
                    while (b.flag) {
                        try {
                            Bread.class.wait();
                        } catch (InterruptedException i) {
                        }
                    }
                    produce("面包");
                    System.out.println(Thread.currentThread().getName() + "----生产者------" + b.name);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException i) {
                    }
                    b.flag = true;
                    Bread.class.notifyAll();
                }
            }
        }
    }

    //描述消费者
    static class Consumer implements Runnable {
        private Bread b;

        Consumer(Bread b) {
            this.b = b;
        }

        public String consume() {
            return b.name;
        }

        public void run() {
            while (true) {
                synchronized (Bread.class) {
                    while (!b.flag) {
                        try {
                            Bread.class.wait();
                        } catch (InterruptedException i) {
                        }
                    }
                    System.out.println(Thread.currentThread().getName() + "----消费者-------------" + consume());
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException i) {
                    }
                    b.flag = false;
                    Bread.class.notifyAll();
                }
            }
        }
    }


    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);

    }




}
