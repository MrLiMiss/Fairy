package com.tengfei.fairy.designPattern.decorateion;

/**
 * @ Description :定义装饰器超类
 * @ Author 李腾飞
 * @ Time 2022/3/13   8:36 PM
 * @ Version :
 */
public class DecoratorBase implements IPerson{
    IPerson iPerson;
    public DecoratorBase(IPerson iPerson){
        this.iPerson = iPerson;
    }

    @Override
    public void show() {
        iPerson.show();
    }
}
