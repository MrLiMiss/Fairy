package com.tengfei.fairy.javaBase.classloader;

/**
 * @ Description :类不同加载方式，结果不同
 *  //1、 使用ClassLoader.loadClass()来加载类，不会执行初始化块 loader.loadClass("Fdd");
 *  // 2、 使用Class.forName()来加载类，默认会执行初始化块 Class.forName("Fdd");、
 *  // 3、使用Class.forName()来加载类，指定ClassLoader，初始化时不执行静态块 Class.forName("Fdd", false, loader);
 * @ Author 李腾飞
 * @ Time 2020-09-02   14:07
 * @ Version :
 */

public class FDDloaderTest {
    public static void main(String[] args) throws ClassNotFoundException {
        ClassLoader loader = FDD.class.getClassLoader();
//        System.out.println("  loader.loadClass(\"FDD\" )-----------");
//        loader.loadClass("com.tengfei.fairy.test.classloader.FDD");
        System.out.println(" Class.forName()----------");
        Class.forName("com.tengfei.fairy.javaBase.classloader.FDD");
//        System.out.println(" Class.forName(\"Fdd\", false, loader) ----------");
//        Class.forName("com.tengfei.fairy.test.classloader.FDD", false, loader);

        System.out.println(loader);


    }


}
