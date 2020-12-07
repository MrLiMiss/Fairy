package com.tengfei.fairy.designPattern.singleton;

/**
 * @ Description :单例模式测试
 * @ Author 李腾飞
 * @ Time 2020-11-27   15:36
 * @ Version :
 */
public class SingletonTest {

    public static void main(String[] args) {
        //饿汉单例测试单例
        testLazy();

    }


    public static void testLazy(){
        System.out.println("---------------------------懒汉模式单例-------------------------------");
        Singleton_Lazy singleton_lazy1=Singleton_Lazy.getInstance2();
        singleton_lazy1.setName("singleton_lazy1");
        Singleton_Lazy singleton_lazy2=Singleton_Lazy.getInstance2();
        singleton_lazy2.setName("singleton_lazy2");

        singleton_lazy1.printInfo();
        singleton_lazy2.printInfo();
        if (singleton_lazy1==singleton_lazy2){
            System.out.println("创建的是同一个实例");
        }else {
            System.out.println("创建的不是同一个实例");
        }
    }
}
