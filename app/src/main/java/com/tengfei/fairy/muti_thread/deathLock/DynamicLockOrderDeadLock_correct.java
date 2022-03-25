package com.tengfei.fairy.muti_thread.deathLock;

/**
 * @ Description :动态的锁顺序死锁解决方法：使用System.identifyHashCode来定义锁的顺 序。确保所有的线程都以相同的顺序获得锁。
 * @ Author 李腾飞
 * @ Time 2022/3/25   11:25 AM
 * @ Version :
 */

import android.accounts.Account;

/**
 * 解决办法
 */
class DynamicLockOrderDeadLock_correct {
    private final Object myLock = new Object();

    public void transefMoney(final Account fromAccount, final Account toAccount, final Double amount) {
        class Helper {
            public void transfer() {
                //...
//            1、    fromAccount.minus(amount);
//            2、    toAccount.add(amount);
                //...
            }
        }
        int fromHash = System.identityHashCode(fromAccount);
        int toHash = System.identityHashCode(toAccount);
        if (fromHash < toHash) {
            synchronized (fromAccount) {
                synchronized (toAccount) {
                    new Helper().transfer();
                }
            }
        }
    }
}