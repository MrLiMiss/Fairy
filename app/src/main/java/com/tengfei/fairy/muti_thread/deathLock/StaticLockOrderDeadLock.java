package com.tengfei.fairy.muti_thread.deathLock;

/**
 * @ Description :静态的所顺序死锁
 *
 *   a和b两个方法都需要获得A锁和B锁。一个线程执行a方法且已经获得了A锁，在等 待B锁；
 *   另一个线程执行了b方法且已经获得了B锁，在等待A锁。这种状态，就是 发生了静态的锁顺序死锁。
 *
 * 解决方法：所有需要多个锁的线程，都要以相同的顺序来 获得锁。
 * @ Author 李腾飞
 * @ Time 2022/3/25   11:11 AM
 * @ Version :
 */

class StaticLockOrderDeadLock {

    ////可能发生静态锁顺序死锁的代码
    private final Object lockA = new Object();
    private final Object lockB = new Object();

    public void a() {
        synchronized (lockA) {
            synchronized (lockB) {
                System.out.println("function a");
            }
        }
    }

    public void b() {
        synchronized (lockB) {
            synchronized (lockA) {
                System.out.println("function b");
            }
        }
    }
}

