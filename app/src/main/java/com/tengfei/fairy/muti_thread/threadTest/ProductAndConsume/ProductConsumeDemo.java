package com.tengfei.fairy.muti_thread.threadTest.ProductAndConsume;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @ Description :
 * <p>
 * synchronized, wait, notify结合:典型场景生产者
 * 消费者问题
 * @ Author 李腾飞
 * @ Time 2/8/22   2:05 PM
 * @ Version :
 */
public class ProductConsumeDemo {

    public static int MAX_PRODUCT = 100;
    public static int MIN_PRODUCT = 0;
    private Queue<Integer> queue = new LinkedList<Integer>();


    /**
     * 消费者
     */
    class Consumer implements Runnable {

        @Override
        public void run() {
            while (true) {
                consume();//消费

            }
        }

        /**
         * 消费者从店员取产品
         */
        public synchronized void consume() {
            while (true){
                synchronized (queue){
                    while (queue.size() <= MIN_PRODUCT) {
                        queue.notify();
                        System.out.println("缺货,稍候再取");
                        try {
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    queue.poll();
                    queue.notify();   //通知等待去的生产者可以生产产品了
                    System.out.println(Thread.currentThread().getName() + "：消费者消费一个产品，此时库存" + queue.size() + "个产品.");
                    try {
                        // 消费一个1S
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }

        }
    }


    /**
     * 生产者
     */
    class Producter implements Runnable {

        @Override
        public void run() {
            while (true) {
                produce();//生产
            }

        }

        /**
         * 生产者生产出来的产品交给店员
         */
        public  void produce() {
            while (true){
                synchronized (queue){
                    while (queue.size() >= MAX_PRODUCT) {
                        queue.notify();
                        System.out.println("产品已满,请稍候再生产");
                        try {
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    queue.add(1);
                    queue.notify();   //通知等待区的消费者可以取出产品了
                    System.out.println(Thread.currentThread().getName() + "：生产1个产品,此时库存" + queue.size() + "个产品.");
                    try {
                        // 生产一个0.5S
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }

            }

        }
    }

    public static void main(String[] args) {
        ProductConsumeDemo productConsumeDemo = new ProductConsumeDemo();
        Consumer consumer = productConsumeDemo.new Consumer();
        Producter producter = productConsumeDemo.new Producter();

        new Thread(producter, "生产者1").start();
        new Thread(consumer, "消费者2").start();

    }


}
