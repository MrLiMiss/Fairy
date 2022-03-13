package com.tengfei.fairy.designPattern.factory;

/**
 * @ Description :  简单工厂模式
 *      优点：
 *      1、工厂类含有必要的判断逻辑，可以决定在什么时候创建哪一个产品类的实例，客户端可以免除直接创建产品对象的责任，而仅仅“消费”产品；简单工厂模式通过这种做法实现了对责任的分割，它提供了专门的工厂类用于创建对象；
 * 客户端无须知道所创建的具体产品类的类名，只需要知道具体产品类所对应的参数即可，对于一些复杂的类名，通过简单工厂模式可以减少使用者的记忆量；
 *      2、通过引入配置文件，可以在不修改任何客户端代码的情况下更换和增加新的具体产品类，在一定程度上提高了系统的灵活性。
 *      缺点：
 *      1、不易拓展，一旦添加新的产品类型，就不得不修改工厂的创建逻辑；
 *      2、产品类型较多时，工厂的创建逻辑可能过于复杂，一旦出错可能造成所有产品的创建失败，不利于系统的维护。
 *
 * @ Author 李腾飞
 * @ Time 2022/3/13   5:27 PM
 * @ Version :
 */
public class SimpleFactoryPattern {
    public static String createProduct(String product) {
        String result = null;
        switch (product) {
            case "Mocca":
                result = "摩卡";
                break;
            case "Latte":
                result = "拿铁";
                break;
            default:
                result = "其他";
                break;
        }
        return result;
    }
}
