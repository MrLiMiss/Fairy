package com.tengfei.fairy.muti_thread.deathLock;

import android.accounts.Account;

/**
 * @ Description :动态的锁顺序死锁
 * <p>
 * 动态的锁顺序死锁是指两个线程调用同一个方法时，传入的参数颠倒造成的死锁。
 * 如下代码，一个线程调用了transferMoney方法并传入参数accountA,accountB；
 * 另 一个线程调用了transferMoney方法并传入参数accountB,accountA。
 * 此时就可能发 生在静态的锁顺序死锁中存在的问题，
 * 即：第一个线程获得了accountA锁并等待 accountB锁，第二个线程获得了accountB锁并等待accountA锁。
 * <p>
 * 动态的锁顺序死锁解决方法：使用System.identifyHashCode来定义锁的顺 序。确保所有的线程都以相同的顺序获得锁。
 * @ Author 李腾飞
 * @ Time 2022/3/25   11:16 AM
 * @ Version :
 */

class DynamicLockOrderDeadLock {
    //可能产生死锁的代码
    public void transefMoney(Account fromAccount, Account toAccount, Double amount) {
        synchronized (fromAccount) {
            synchronized (toAccount) {
                //...

                //1、fromAccount.minus(amount);
                //2、toAccount.add(amount);

                //...
            }
        }
    }
}


