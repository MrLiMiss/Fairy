package com.tengfei.fairy.animation.valueAnim;

import android.animation.TypeEvaluator;

/**
 * @ Description : 定义point 的估值器，确定从开始值到结束值，如何完成
 * @ Author 李腾飞
 * @ Time 2022/4/26   11:42 AM
 * @ Version :
 */

public class PointEvaluator implements TypeEvaluator {

    @Override
    public Object evaluate(float fraction, Object startValue, Object endValue) {
        Point startPoint = (Point) startValue;
        Point endPoint = (Point) endValue;
        float x = startPoint.getX() + fraction * (endPoint.getX() - startPoint.getX());
        float y = startPoint.getY() + fraction * (endPoint.getY() - startPoint.getY());
        Point point = new Point(x, y);
        return point;
    }

}
