package com.tengfei.fairy.animation.valueAnim;

import android.animation.TypeEvaluator;

/**
 * @ Description : 自定义估值器，告诉动画系统如何从初始值过度到结束值
 * @ Author 李腾飞
 * @ Time 2022/4/26   11:40 AM
 * @ Version :
 */
public class FloatEvaluator implements TypeEvaluator {
    public Object evaluate(float fraction, Object startValue, Object endValue) {
        float startFloat = ((Number) startValue).floatValue();
        return startFloat + fraction * (((Number) endValue).floatValue() - startFloat);
    }
}
