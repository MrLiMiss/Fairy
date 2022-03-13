package com.tengfei.fairy.designPattern.adapterPattern;

/**
 * @ Description :适配器
 * @ Author 李腾飞
 * @ Time 2022/3/13   9:42 PM
 * @ Version :
 */
class AdapterMicroUSB implements MicroUSB {
    private TypeC typeC;

    public AdapterMicroUSB(TypeC typeC) {
        this.typeC = typeC;
    }

    @Override
    public void charger() {
        typeC.charger();
    }
}
