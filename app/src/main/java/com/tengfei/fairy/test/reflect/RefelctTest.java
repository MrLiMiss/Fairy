package com.tengfei.fairy.test.reflect;

import android.app.Activity;

import com.tengfei.fairy.utils.Logs;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * @ Description :
 * @ Author 李腾飞
 * @ Time 2020-09-14   17:11
 * @ Version :
 */
public class RefelctTest {


    public static void main(String[] args) throws Exception {
        Son son = new Son();
        Class clazz = son.getClass();
        son.setIDNumber("123123321");
        son.setName("adam");
        son.setAge(3);
        new ReflectUtil().copy(son);


        myConstuctor(clazz);

        myField(clazz);


    }


    //field 相关
    public static void myField(Class clazz){
        System.out.println("获取field相关：");
        //获取此类的所有的公共（public）的字段，返回 Field 对象的一个数组
        Field[] fields = clazz.getFields();
        Field[] allFields = clazz.getDeclaredFields();//获取类中所有的属性(public、protected、default、private)，但不包括继承的属性，返回 Field 对象的一个数组
        //获取字段名
        String fieldName = clazz.getName();
        System.out.println("fieldName:" + fieldName);
        for (Field field : allFields) {
            int xiushifu = field.getModifiers();//
            System.out.println("修饰符:" + xiushifu);
            Class aClass = field.getType();//变量类型 int标识（private为2  public 为1 protected 为 4  public static 为 9 ）
            String filedname2 = aClass.getName();
            System.out.println("变量类型：" + filedname2);

        }
    }


    /**
     * 获取类的所有构造函数
     * @param clazz
     */
    public static void myConstuctor(Class clazz){
        System.out.println("获取构建函数Constuctor相关：");
        String className=clazz.getName();
        Constructor[] constructors=clazz.getConstructors();
        for(Constructor constructor:constructors){
            int mod = constructor.getModifiers();    // 输出修饰域和方法名称
            System.out.print(Modifier.toString(mod) + " " + className + "(");
            Class[] parameterTypes=constructor.getParameterTypes();// 获取指定构造方法的参数的集合
            for (int j = 0; j < parameterTypes.length; j++) {    // 输出打印参数列表
                System.out.print(parameterTypes[j].getName());
                if (parameterTypes.length > j+1) {
                    System.out.print(", ");
                }
            }
            System.out.println(")");

        }

    }


}
