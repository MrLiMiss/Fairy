package com.tengfei.fairy.widget.view_viewgroup;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.tengfei.fairy.R;

/**
 * @ Description :  自定义 view  实现 textView 效果
 *     xml 文件设置属性  java 文件  set get相应属性
 *    自定义view  无需 layout  只需要  onMeasure()  onDraw（）
 *    onMeasure 中MeasureSpec  为父控件对view的控制。
 *    mode == AT_MOST  表示 view 最大可以 measureSpec大小
 *    mode == EXACTLY  表示 view 必须确切 measureSpec大小
 * @ Author 李腾飞
 * @ Time 2022/4/1   3:12 PM
 * @ Version :
 */
public class MyView extends View {
    private String text;
    private Paint mPaint;
    private Rect mTextBounds;

    private int width;//view 宽度
    private int height;//view 高度


    public MyView(Context context) {
        this(context,null);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array=getContext().obtainStyledAttributes(attrs,R.styleable.MyView);
        text=array.getString(R.styleable.MyView_my_text);
        array.recycle();
        mPaint=new Paint();
        mPaint.setTextSize(50);
        mTextBounds = new Rect();
        mPaint.getTextBounds(text,0,text.length(),mTextBounds);//完成初始设置
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int specModel=MeasureSpec.getMode(widthMeasureSpec);
        int specWidth=MeasureSpec.getSize(widthMeasureSpec);
       if (specModel ==MeasureSpec.EXACTLY){//对应有确切值，如100px  match_parent
           width=specWidth;
       }else if(specModel==MeasureSpec.AT_MOST){//对应wrap_content
           width=mTextBounds.width()+getPaddingLeft()+getPaddingRight();
       }

       specModel=MeasureSpec.getMode(heightMeasureSpec);
       int specHeight=MeasureSpec.getSize(heightMeasureSpec);
        if (specModel ==MeasureSpec.EXACTLY){//对应有确切值，如100px  match_parent
            height=specHeight;
        }else if(specModel==MeasureSpec.AT_MOST){//对应wrap_content
            height=mTextBounds.height()+getPaddingTop()+getPaddingBottom();
        }
        setMeasuredDimension(width,height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawText(text,getPaddingLeft()+0,mTextBounds.height()+getPaddingTop(),mPaint);
    }
}
