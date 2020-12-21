package com.tengfei.fairy.designPattern.singleton;

/**
 * @ Description :饿汉模式 单例
 * 饿汉式，从名字上也很好理解，就是“比较勤”，
 * 实例在初始化的时候就已经建好了，不管你有没有用到，都先建好了再说。
 * 好处是没有线程安全的问题，坏处是浪费内存空间。
 * @ Author 李腾飞
 * @ Time 2020-11-30   17:24
 * @ Version :
 */
public class Singleton_EHan {
    private static Singleton_EHan instance=new Singleton_EHan();//饿汉
    private Singleton_EHan(){}//私有构造方法
    public static Singleton_EHan getInstance(){
        return instance;
    }


}
