package com.tengfei.fairy.designPattern.factory.AbstractFactory;

/**
 * @ Description :咖啡工厂
 * @ Author 李腾飞
 * @ Time 2022/3/13   5:52 PM
 * @ Version :
 */
public class CoffeeFactory extends AbstoryFactory {
    @Override
    public String createProduct(String product) {
        String result = null;
        switch (product) {
            case "Mocca":
                result = "摩卡";
                break;
            case "Latte":
                result = "拿铁";
                break;
            default:
                result = "其他咖啡";
                break;
        }
        return result;
    }
}
