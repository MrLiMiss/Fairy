package com.tengfei.fairy.event_conflict.different.in;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ListView;

/**
 * @ Description :内部子view 处理点击事件
 * @ Author 李腾飞
 * @ Time 2022/3/16   3:30 PM
 * @ Version :
 */
public class ListViewEx extends ListView {
    private static final String TAG = "ListViewEx";

    private HorizontalScrollViewEx2 mHorizontalScrollViewEx2;

    // 分别记录上次滑动的坐标

    private int mLastX = 0;
    private int mLastY = 0;

    public ListViewEx(Context context) {
        super(context);
    }

    public ListViewEx(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ListViewEx(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setHorizontalScrollViewEx2(
            HorizontalScrollViewEx2 horizontalScrollViewEx2) {
        mHorizontalScrollViewEx2 = horizontalScrollViewEx2;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                //ViewGroup有一个disallowIntercept开关，可以设置此ViewGroup是否屏蔽onInterceptTouchEvent事件。
                //如果开启此开关，则此ViewGroup跳过自身的onInterceptTouchEvent事件，直接dispatchTouchEvent到子View
                mHorizontalScrollViewEx2.requestDisallowInterceptTouchEvent(true);
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                int deltaX = x - mLastX;
                int deltaY = y - mLastY;
                Log.d(TAG, "dx:" + deltaX + " dy:" + deltaY);
                if (Math.abs(deltaX) > Math.abs(deltaY)) {
                    mHorizontalScrollViewEx2.requestDisallowInterceptTouchEvent(false);
                }
                break;
            }
            case MotionEvent.ACTION_UP: {
                break;
            }
            default:
                break;
        }

        mLastX = x;
        mLastY = y;
        return super.dispatchTouchEvent(event);
    }
}

