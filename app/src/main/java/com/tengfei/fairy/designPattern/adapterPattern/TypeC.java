package com.tengfei.fairy.designPattern.adapterPattern;

/**
 * @ Description :
 * @ Author 李腾飞
 * @ Time 2022/3/13   9:43 PM
 * @ Version :
 */
public class TypeC implements ITypeC {
    @Override
    public void charger() {
        System.out.println("TypeC 充电");
    }
}
