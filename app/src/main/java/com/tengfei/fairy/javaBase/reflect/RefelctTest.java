package com.tengfei.fairy.javaBase.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * @ Description :java 反射测试
 * @ Author 李腾飞
 * @ Time 2020-09-14   17:11
 * @ Version :
 */
public class RefelctTest {


    public static void main(String[] args) throws Exception {
        Son son = new Son();
        Class clazz=son.getClass();

//        myRefelct();

        //构造函数
//        myConstuctor(clazz);

        //所有public 非public属性
      getAllField(son);
//        getPublicField(son);


    }

    /**
     * 获取打印 所有（public & 非public ）field 变量 类型+属性
     * @param
     * @throws
     * @throws
     */
    public static void getAllField(Object object) throws NoSuchFieldException, IllegalAccessException {
        Class clazz=object.getClass();
        System.out.println("获取field相关：");
        System.out.println("public & 非public 属性");
        Field[] allFields = clazz.getDeclaredFields();//获取类中所有的属性(public、protected、default、private)，但不包括继承的属性，返回 Field 对象的一个数组
        //获取字段名
        String fieldName = clazz.getName();
        System.out.println("fieldName:" + fieldName);
        for (Field field : allFields) {
            field.setAccessible(true);
            int xiushifu = field.getModifiers();//

            Class aClass = field.getType();//变量类型 int标识（private为2  public 为1 protected 为 4  public static 为 9 ）
            String fieldType = aClass.getName();

            System.out.println("修饰符:" + xiushifu+",变量类型：" + fieldType+",value:"+field.get(object));


        }
    }

    /**
     *
     * 打印所有public 属性 和属性的值
     * @param
     * @throws
     * @throws
     */
    public static void getPublicField(Object obj) throws NoSuchFieldException, IllegalAccessException {
        Class clazz=obj.getClass();
        System.out.println("public 属性");
        //获取此类的所有的公共（public）的字段，返回 Field 对象的一个数组
        Field[] fields = clazz.getFields();
        for(Field field:fields){
            Class cl = field.getType();    // 属性的类型

            int md = field.getModifiers();    // 属性的修饰域

            Field f = clazz.getField(field.getName());    // 属性的值
            f.setAccessible(true);
            Object value = (Object)f.get(obj);

            // 判断属性是否被初始化
            if (value == null) {
                System.out.println(Modifier.toString(md) + " " + cl + " : " + field.getName());
            } else {
                System.out.println(Modifier.toString(md) + " " + cl + " : " + field.getName() + " = " + value.toString());
            }
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

    public static void myRefelct() throws Exception {
        Son son = new Son();
        Class clazz = son.getClass();
        son.setIDNumber("123123321");
        son.setName("adam");
        son.setAge(3);
        new ReflectUtil().copy(son);
    }

    public static void myMethod(){

    }

}
