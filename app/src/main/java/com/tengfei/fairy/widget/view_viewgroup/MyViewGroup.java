package com.tengfei.fairy.widget.view_viewgroup;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * @ Description :  自定义 viewgroup  实现 垂直linerLayout 效果
 * @ Author 李腾飞
 * @ Time 2022/4/1   4:25 PM
 * @ Version :
 */
public class MyViewGroup extends ViewGroup {

    private int mWidth;
    private int mHeight;

    public MyViewGroup(Context context) {
        this(context, null);
    }

    public MyViewGroup(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //没有子view的情况：
        int childCount = getChildCount();
        if (childCount == 0) {
            mWidth = measureHeightAndWidth(widthMeasureSpec);
            mHeight = measureHeightAndWidth(heightMeasureSpec);
            setMeasuredDimension(mWidth, mHeight);
        } else {
            int childViewWidth = 0;//子view 宽
            int childViewHeight = 0;//子view  高
            int totalChildViewHeight = 0;//子view总高
            int totalChildMarginTop = 0;//累计margintop
            int totalChildMarginBottom = 0;//累计marginBottom

            for (int i = 0; i < childCount; i++) {
                View childView = getChildAt(i);
                //使用之前需要先测量子view的值
                measureChild(childView, widthMeasureSpec, heightMeasureSpec);
                //layoutParams 可获取  margin，子view的 margin值 params
                MarginLayoutParams lp = (MarginLayoutParams) childView.getLayoutParams();
                //linerLayout 自己规则
                childViewWidth = Math.max(childViewWidth, childView.getMeasuredWidth() + lp.leftMargin + lp.rightMargin);
                totalChildViewHeight += childView.getMeasuredHeight();

                totalChildMarginTop += lp.topMargin;
                totalChildMarginBottom += lp.bottomMargin;
            }
            // 整个view宽度=子view宽度+子view  PaddingLeft+子view  PaddingRight
            mWidth = childViewWidth + getPaddingLeft() + getPaddingRight();
            mHeight = totalChildViewHeight + totalChildMarginTop + totalChildMarginBottom + getPaddingTop() + getPaddingBottom();

            setMeasuredDimension(measureWidthAndHeight(widthMeasureSpec, mWidth), measureWidthAndHeight(heightMeasureSpec, mHeight));


        }
    }

    /**
     * 计算宽高
     *
     * @param measureSpec
     * @param size
     * @return
     */
    private int measureWidthAndHeight(int measureSpec, int size) {
        int result = 0;
        int measureMode = MeasureSpec.getMode(measureSpec);
        int measureSize = MeasureSpec.getSize(measureSpec);
        if (MeasureSpec.EXACTLY == measureMode) {
            result = measureSize;
        } else if (MeasureSpec.AT_MOST == measureMode) {
            result = size;
        }
        return result;
    }

    private int measureHeightAndWidth(int measureSpec) {
        int result = 0;
        if (MeasureSpec.AT_MOST == MeasureSpec.getMode(measureSpec)) {
            result = 0;
        } else if (MeasureSpec.EXACTLY == MeasureSpec.getMode(measureSpec)) {
            result = MeasureSpec.getSize(measureSpec);
        }
        return result;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int left, top, right, bottom;
        int childCount = getChildCount();
        int countTop = 0;
        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
            MarginLayoutParams layoutParams = (MarginLayoutParams) view.getLayoutParams();
            //left=子viewleftMargin+父容器paddingleft
            left = layoutParams.leftMargin+getPaddingLeft();
            //第一个需要加上父容器padding
            top = getPaddingTop()+countTop + layoutParams.topMargin;
            right = left + view.getMeasuredWidth();
            bottom = top + view.getMeasuredHeight();
            view.layout(left, top, right, bottom);
            //bottom-top =子view height
            countTop += (bottom - top)  + layoutParams.topMargin + layoutParams.bottomMargin;
        }

    }


    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }
}
