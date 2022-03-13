package com.tengfei.fairy.designPattern.adapterPattern;

/**
 * @ Description :适配器模式
 *
 * ：适配器模式是将一个类的接口变成客户端所期望的另一种接口，从而使原本因接口不匹配而无法一起工作的两个类能够在一起工作。
 *
 *      优点：
 *      1、可以让两个没有关联的类一起运行，起着中间转换的作用；
 *      2、灵活性好，不会破坏原有的系统。
 *      缺点：
 *      1、过多地使用适配器，容易使代码结构混乱，如明明看到调用的是 A 接口，内部调用的却是 B 接口的实现。
 * @ Author 李腾飞
 * @ Time 2020-11-27   15:40
 * @ Version :
 */
public class AdapterTest {
    public static void main(String[] args) {
        TypeC typeC = new TypeC();
        MicroUSB microUSB = new AdapterMicroUSB(typeC);
        //调用适配器，完成MicroUSB--> TypeC
        microUSB.charger();

    }
}
