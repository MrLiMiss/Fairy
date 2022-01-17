package com.tengfei.fairy.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * @ Description :跟随手指移动简单小球
 * @ Author 李腾飞
 * @ Time 2020/12/22   13:33
 * @ Version :
 */
public class DrawView extends View {
    public float currentX=40;
    public float currenty=50;
    //定义并创建画笔
    Paint paint=new Paint();
    public DrawView(Context context){
        super(context);
    }

    public DrawView(Context context, AttributeSet sets){
        super(context,sets);
    }

    @Override
    public void onDraw(Canvas canvas){
        super.onDraw(canvas);
        //设置画笔颜色
        paint.setColor(Color.RED);
        //绘画圆作为小球
       canvas.drawCircle(currentX,currenty,15,paint);
    }


    //为该组件的触事件，重写处理方法
    @Override
    public boolean onTouchEvent(MotionEvent motionEvent){
        currentX=motionEvent.getX();
        currenty=motionEvent.getY();
        invalidate();
        //返回true 表示已经处理该事件
        return true;
    }

}
