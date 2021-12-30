package com.tengfei.fairy.javaBase.integerTest;

import java.lang.reflect.Field;

/**
 * @ Description :
 * @ Author 李腾飞
 *
 * 在Java中，只有一种参数传递方式，就是值传递。但是，当参数传的是基本类型时，传的是值的拷贝，对拷贝变量的修改不影响原变量；
 * 当传的是引用类型时，传的是引用地址的拷贝，但是拷贝的地址和真实地址指向的都是同一个真实数据，因此可以修改原变量中的值；
 * 当传的是Integer类型时，虽然拷贝的也是引用地址，指向的是同一个数据，但是Integer的值不能被修改，因此无法修改原变量中的值。
 *
 * @ Time 12/30/21   2:38 PM
 * @ Version :
 */
public class IntegerExample {

    public static void main(String[] args){
        Integer a=1;
        Integer b=2;
        System.out.println("交换前：a="+a+",b="+b);
//        swap(a,b);
        swap2(a,b);
        System.out.println("swap交换后：a="+a+",b="+b);
    }

    private static void swap(Integer a,Integer b){
        try {
            Field field=Integer.class.getDeclaredField("value");
            Integer temp= a;//由于integer的缓存机制，(-128,127)范围内的数据，这里并不会产生一个新的temp实例，意味着temp变量和a变量指向的内存地址是同一个。
            field.setAccessible(true); //针对private修饰的变量，需要通过该方法设置。
            field.set(a,b);//当通过field.set方法，把a内存地址的值通过反射修改成b以后，那么此时a的值应该是2。注意：由于内存地址的值变成了2，而temp这个变量又指向该内存地址，因此temp的值自然就变成了2.
            field.set(b,temp);//temp 赋值给b，修改b属性的值，此时temp的值时2，所以得到的结果b也变成了2.
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private static void swap2(Integer a,Integer b){
        try {
            Field field=Integer.class.getDeclaredField("value");
            Integer temp= new Integer(a);//创建新的integer对象
            field.setAccessible(true); //针对private修饰的变量，需要通过该方法设置。
            field.set(a,b);
            field.set(b,temp);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
