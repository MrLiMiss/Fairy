package com.tengfei.fairy.muti_thread.deathLock;

/**
 * @ Description :静态的所顺序死锁解决方法：所有需要多个锁的线程，都要以相同的顺序来 获得锁。
 * @ Author 李腾飞
 * @ Time 2022/3/25   11:26 AM
 * @ Version :
 */
class StaticLockOrderDeadLock_correct {

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
            synchronized (lockA) {
                synchronized (lockB) {
                    System.out.println("function b");
                }
            }
        }

}
