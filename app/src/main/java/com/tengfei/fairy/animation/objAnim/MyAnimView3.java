package com.tengfei.fairy.animation.objAnim;

        import android.animation.AnimatorSet;
        import android.animation.ObjectAnimator;
        import android.animation.ValueAnimator;
        import android.content.Context;
        import android.graphics.Canvas;
        import android.graphics.Color;
        import android.graphics.Paint;
        import android.util.AttributeSet;
        import android.view.View;
        import android.view.animation.AccelerateInterpolator;
        import android.view.animation.BounceInterpolator;

        import com.tengfei.fairy.animation.valueAnim.Point;
        import com.tengfei.fairy.animation.valueAnim.PointEvaluator;

/**
 * @ Description :插值器
 * @ Author 李腾飞
 * @ Time 2022/4/26   11:57 AM
 * @ Version :
 */
public class MyAnimView3 extends View {

    public static final float RADIUS = 50f;

    private Point currentPoint;

    private String color;

    private Paint mPaint;

    public MyAnimView3(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLUE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (currentPoint == null) {
            currentPoint = new Point(RADIUS, RADIUS);
            drawCircle(canvas);
            startAnimation();
        } else {
            drawCircle(canvas);
        }
    }



    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
        mPaint.setColor(Color.parseColor(color));
        invalidate();
    }

    private void drawCircle(Canvas canvas) {
        float x = currentPoint.getX();
        float y = currentPoint.getY();
        canvas.drawCircle(x, y, RADIUS, mPaint);
    }

    private void startAnimation() {
        Point startPoint = new Point(getWidth() / 2, RADIUS);
        Point endPoint = new Point(getWidth() / 2, getHeight() - RADIUS);
        ValueAnimator anim = ValueAnimator.ofObject(new PointEvaluator(), startPoint, endPoint);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currentPoint = (Point) animation.getAnimatedValue();
                invalidate();
            }
        });
        //线性加速补间器
//        anim.setInterpolator(new AccelerateInterpolator(2f));
        //自由落体补间器
        anim.setInterpolator(new BounceInterpolator());
        anim.setDuration(2000);
        anim.start();

    }
}

