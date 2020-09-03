package com.tengfei.fairy.test.classloader;

/**
 * @ Description :类加载test
 * @ Author
 * @ Time 2020-09-02   13:18
 * @ Version :
 */
public class ClassLoadTest {
    public static void main(String[] args){
        ClassLoader loder=Thread.currentThread().getContextClassLoader();
        System.out.println(loder);
        System.out.println(loder.getParent());
        System.out.println(loder.getParent().getParent());
    }
}
