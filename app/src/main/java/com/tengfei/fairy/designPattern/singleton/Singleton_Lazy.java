package com.tengfei.fairy.designPattern.singleton;

/**
 * @ Description :单例 懒汉模式
 * @ Author 李腾飞
 * @ Time 2020-11-30   17:25
 * @ Version :
 */
public class Singleton_Lazy {
    private static Singleton_Lazy singleton_lazy=null;
    String name=null;
    //private  构造函数
    private Singleton_Lazy() {

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void printInfo() {
        System.out.println("the name is " + name);
    }



    // 静态工厂方法，没有考虑线程安全问题，它是线程不安全的，并发环境下很可能出现多个Singleton实例
    public static Singleton_Lazy getInstance(){
        if(singleton_lazy==null){
          singleton_lazy=new Singleton_Lazy();
        }
        return singleton_lazy;
    }

    /** getInstance1()方法上 加同步
     * @return
     */
    public static synchronized Singleton_Lazy getInstance1(){
        if(singleton_lazy==null){
            singleton_lazy=new Singleton_Lazy();
        }
        return singleton_lazy;
    }

    /** 双重检查锁定：
     * 综合了懒汉式和饿汉式两者的优缺点整合而成。
     * 在synchronized关键字内外都加了一层 if 条件判断，这样既保证了线程安全，又比直接上锁提高了执行效率，还节省了内存空间。
     * @return
     */
    public static  Singleton_Lazy getInstance2(){
        if(singleton_lazy==null){
            synchronized (Singleton_Lazy.class){
                if (singleton_lazy==null){
                    singleton_lazy=new Singleton_Lazy();
                }

            }
        }
        return singleton_lazy;
    }



    //-------------------------------------------静态内部类实现单例--------------------------------------------
   private static class LazyHolder{
       private static final Singleton_Lazy INSTANCE = new Singleton_Lazy();
   }
    public static final Singleton_Lazy getInstance3() {
        return LazyHolder.INSTANCE;
    }


}
