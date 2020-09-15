package com.tengfei.fairy.test;

import com.tengfei.fairy.test.reflect.Father;

/**
 * @ Description :
 * @ Author 李腾飞
 * @ Time 2020-09-15   13:43
 * @ Version :
 */
public class Daughter extends Father {

    public static void main(String[] args) throws Exception {
        String fatherName=Father.name;//protect 修饰的类 属性以及方法，只能被本身的方法盒子类访问，即使子类在不同的包中也可以访问。
//        int fatherAge=Father.FatherAge;//父类Father中 默认修复符 修饰的属性，仅能类内部，或者同一包内调用
        System.out.println("Daughter,fatherName:"+fatherName);
    }

}
