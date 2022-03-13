package com.tengfei.fairy.designPattern.factory.AbstractFactory;

/**
 * @ Description :啤酒工厂
 * @ Author 李腾飞
 * @ Time 2022/3/13   5:53 PM
 * @ Version :
 */
public class BeerFactory extends AbstoryFactory {
    @Override
    public String createProduct(String product) {
        String result = null;
        switch (product) {
            case "Hans":
                result = "汉斯";
                break;
            case "Yanjing":
                result = "燕京";
                break;
            default:
                result = "其他啤酒";
                break;
        }
        return result;
    }
}
