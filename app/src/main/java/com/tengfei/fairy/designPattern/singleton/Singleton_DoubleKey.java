package com.tengfei.fairy.designPattern.singleton;

/**
 * @ Description :单例 双重检测：
 * 双检锁，又叫双重校验锁，综合了懒汉式和饿汉式两者的优缺点整合而成。
 * 看上面代码实现中，特点是在synchronized关键字内外都加了一层 if 条件判断，
 * 这样既保证了线程安全，又比直接上锁提高了执行效率，还节省了内存空间。
 * @ Author 李腾飞
 * @ Time 2020-11-30   17:20
 * @ Version :
 */
public class Singleton_DoubleKey {
    private static Singleton_DoubleKey instance;

    private Singleton_DoubleKey() {
    }

    public static Singleton_DoubleKey getInstance() {
        if (instance == null) {
            synchronized (Singleton_DoubleKey.class) {
                if (instance == null) {
                    instance = new Singleton_DoubleKey();
                }
            }
        }
        return instance;
    }

}
