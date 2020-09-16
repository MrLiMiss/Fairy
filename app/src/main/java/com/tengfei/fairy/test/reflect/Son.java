package com.tengfei.fairy.test.reflect;

import java.net.IDN;

/**
 * @ Description : 反射机制 测试类
 * @ Author 李腾飞
 * @ Time 2020-09-14   15:33
 * @ Version :
 */
public class Son extends Father {
    //被private static修饰的属性仅仅可以被静态方法调用，但是只能被本类中的方法（可以是非静态的）调用，在外部创建这个类的对象或者直接使用这个类访问都是非法的。


    public static String sex = "1";
    public int age=12;
    private String IDNumber;//身份证号
    protected String name;


    public Son() {
    }

    public Son(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Son(String name, int age, String idnumber) {
        this.name = name;
        this.age = age;
        this.IDNumber = idnumber;
    }

    public static String getSex() {
        return sex;
    }

    public static void setSex(String sex) {
        Son.sex = sex;
    }

    public String getIDNumber() {
        return IDNumber;
    }

    public void setIDNumber(String IDNumber) {
        this.IDNumber = IDNumber;
    }


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public class Job {
        public String Address;

    }

    //私有方法
    private void say() {
        System.out.println("private say()...");
    }


}
