package com.tengfei.fairy.designPattern.decorateion;

/**
 * @ Description : 定义具体装饰器  穿夹克
 * @ Author 李腾飞
 * @ Time 2022/3/13   8:38 PM
 * @ Version :
 */
class Jacket extends DecoratorBase {
    public Jacket(IPerson iPerson) {
        super(iPerson);
    }
    @Override
    public void show() {
        // 执行已有功能
        iPerson.show();
        // 定义新行为
        System.out.println("穿上夹克");
    }
}
