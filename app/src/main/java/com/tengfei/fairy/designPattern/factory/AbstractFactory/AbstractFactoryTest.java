package com.tengfei.fairy.designPattern.factory.AbstractFactory;

/**
 * @ Description :抽象工厂模式
 *                抽象工厂里只声明方法，具体的实现交给子类（子工厂）去实现，这个时候再有新增品类的需求，只需要新创建代码即可。
 * @ Author 李腾飞
 * @ Time 2022/3/13   5:30 PM
 * @ Version :
 */
public class AbstractFactoryTest {
    public static void main(String[] args) {
        // 抽象工厂
        String result = (new CoffeeFactory()).createProduct("Latte");
        System.out.println(result); // output:拿铁
    }

}
