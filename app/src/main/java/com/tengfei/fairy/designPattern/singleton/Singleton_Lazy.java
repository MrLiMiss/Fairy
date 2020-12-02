package com.tengfei.fairy.designPattern.singleton;

/**
 * @ Description :单例 懒汉模式
 * @ Author 李腾飞
 * @ Time 2020-11-30   17:25
 * @ Version :
 */
public class Singleton_Lazy {
    private static Singleton_Lazy singleton_lazy=null;

    private Singleton_Lazy() {

    }

    public static Singleton_Lazy getInstance(){
        if(singleton_lazy==null){
          singleton_lazy=new Singleton_Lazy();
        }
        return singleton_lazy;
    }
}
