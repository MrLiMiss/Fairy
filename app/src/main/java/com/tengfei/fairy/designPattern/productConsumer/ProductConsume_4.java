package com.tengfei.fairy.designPattern.productConsumer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ Description :  多生产者  多消费者 面包 篮子 完成多线程安全（Lock和Conditon 实现）
 * @ Author 李腾飞
 * @ Time 2022/3/13   3:54 PM
 * @ Version :
 */
public class ProductConsume_4 {
    public static void main(String[] args) {
        Basket b = new Basket(20); // the basket size = 20
        Producer pro = new Producer(b);
        Consumer con = new Consumer(b);
        Thread pro_t1 = new Thread(pro);
        Thread pro_t2 = new Thread(pro);
        Thread pro_t3 = new Thread(pro);
        Thread pro_t4 = new Thread(pro);
        Thread con_t1 = new Thread(con);
        Thread con_t2 = new Thread(con);
        Thread con_t3 = new Thread(con);
        pro_t1.start();
        pro_t2.start();
        pro_t3.start();
        pro_t4.start();
        con_t1.start();
        con_t2.start();
        con_t3.start();
    }


    /**
     *  面包篮子
     */
    public static class Basket {
        private Bread[] arr;
        //the size of basket
        Basket(int size){
            arr = new Bread[size];
        }
        /**
         * the pointer of in and out
         * 放入 和 取出 的指针
         */
        private int in_ptr,out_ptr;
        //how many breads left in basket
        private int left;//剩余面包数量
        private Lock lock = new ReentrantLock();
        private Condition full = lock.newCondition();
        private Condition empty = lock.newCondition();
        //bread into basket
        public void in(){
            lock.lock();
            try{
                while(left == arr.length){
                    try{full.await();} catch (InterruptedException i) {i.printStackTrace();}
                }
                arr[in_ptr] = new Bread("MianBao",Producer.num++);
                System.out.println("Put the bread: "+arr[in_ptr].getName()+"------into basket["+in_ptr+"]");
                left++;
                if(++in_ptr == arr.length){in_ptr = 0;}
                empty.signal();
            } finally {
                lock.unlock();
            }
        }
        //bread out from basket
        public Bread out(){
            lock.lock();
            try{
                while(left == 0){
                    try{empty.await();} catch (InterruptedException i) {i.printStackTrace();}
                }
                Bread out_bread = arr[out_ptr];
                System.out.println("Get the bread: "+out_bread.getName()+"-----------from basket["+out_ptr+"]");
                left--;
                if(++out_ptr == arr.length){out_ptr = 0;}
                full.signal();
                return out_bread;
            } finally {
                lock.unlock();
            }
        }
    }

    /**
     * 面包描述
     */
    public static class Bread {
        private String name;
        Bread(String name,int num){
            this.name = name + num;
        }
        public String getName(){
            return this.name;
        }
    }

    /**
     * 生产者描述
     */
    public static class Producer implements Runnable {
        private Basket basket;
        public static int num = 1; //the first number for Bread's name
        Producer(Basket b){
            this.basket = b;
        }
        public void run(){
            while(true) {
                basket.in();
                try{Thread.sleep(10);}catch(InterruptedException i){}
            }
        }
    }

    /**
     * 消费者
     */
    public static class Consumer implements Runnable {
        private Basket basket;
        private Bread i_get;
        Consumer(Basket b){
            this.basket = b;
        }
        public void run(){
            while(true){
                i_get = basket.out();
                try{Thread.sleep(10);}catch(InterruptedException i){}
            }
        }
    }




}
