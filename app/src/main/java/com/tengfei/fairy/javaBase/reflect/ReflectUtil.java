package com.tengfei.fairy.javaBase.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @ Description :java反射test
 * @ Author 李腾飞
 * @ Time 2020-09-02   13:31
 * @ Version :
 */
public class ReflectUtil {


    /**
     * @param object
     * @return   创建一个和参数objcet同样类型的对象，然后把object对象中的所有属性拷贝到新建的对象中，并将其返回。
     * @throws
     */
    public Object copy(Object object) throws Exception{
        //获得对象的类型
        Class classType=object.getClass();
        System.out.println("Class:"+classType.getName());

        //通过默认构造方法创建一个新的对象
        Object objectCopy=classType.getConstructor(new Class[]{}).newInstance(new Object[]{});

        //获得对象的所有属性
//        getFields()：获得某个类的所有的公共（public）的字段，包括父类中的字段。
//        getDeclaredFields()：获得某个类的所有声明的字段，即包括public、private和proteced，但是不包括父类的申明字段。
        Field fields[]=classType.getDeclaredFields();

        for(int i=0; i<fields.length;i++){
            Field field=fields[i];

            String fieldName=field.getName();
            String firstLetter=fieldName.substring(0,1).toUpperCase();
            //获得和属性对应的getXXX()方法的名字
            String getMethodName="get"+firstLetter+fieldName.substring(1);
            //获得和属性对应的setXXX()方法的名字
            String setMethodName="set"+firstLetter+fieldName.substring(1);

            //获得和属性对应的getXXX()方法
            Method getMethod=classType.getMethod(getMethodName,new Class[]{});
            //获得和属性对应的setXXX()方法
            Method setMethod=classType.getMethod(setMethodName,new Class[]{field.getType()});

            //调用原对象的getXXX()方法
            Object value=getMethod.invoke(object,new Object[]{});
            System.out.println(fieldName+":"+value);
            //调用拷贝对象的setXXX()方法
            setMethod.invoke(objectCopy,new Object[]{value});
        }
        return objectCopy;
    }


}
