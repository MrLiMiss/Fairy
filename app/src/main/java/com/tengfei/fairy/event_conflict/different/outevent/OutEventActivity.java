package com.tengfei.fairy.event_conflict.different.outevent;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.tengfei.fairy.R;
import com.tengfei.fairy.event_conflict.MyUtils;

import android.widget.AdapterView.OnItemClickListener;
import java.util.ArrayList;

/**
 * @ Description :处理方向不一致的 滑动冲突
 *   外部拦截法：1、点击事件都先经过父容器的拦截处理，如果父容器需要此事件就拦截，如果不需要此事件就不拦截。
 *             2、这种处理方法比较符合点击事件的分发机制，外部拦截发需要重写父容器的onInterceptTouchEvent（）
 *             3、onInterceptTouchEvent（）方法父容器不拦截ACTION_DOWN事件，如果拦截ACTION_DOWN事件后续的ACTION_MOVE和ACTION_UP会直接交给父容器处理。
 *             4、对于ACTION_UP 作为最后一个事件必定会传递给父容器，即使onInterceptTouchEvent（） ACTION_UP 返回了false
 * @ Author 李腾飞
 * @ Time 2022/3/16   9:29 AM
 * @ Version :
 */
public class OutEventActivity extends Activity {

    private static final String TAG = "OutEventActivity";
    private HorizontalScrollViewEx mListContainer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outevent);
        Log.d(TAG, "onCreate");
        initView();
    }

    @SuppressLint("SetTextI18n")
    private void initView() {
        LayoutInflater inflater = getLayoutInflater();
        mListContainer = (HorizontalScrollViewEx) findViewById(R.id.container);
        final int screenWidth = MyUtils.getScreenMetrics(this).widthPixels;
        final int screenHeight = MyUtils.getScreenMetrics(this).heightPixels;
        for (int i = 0; i < 3; i++) {
            ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.content_layout, mListContainer, false);
            layout.getLayoutParams().width = screenWidth;
            TextView textView = (TextView) layout.findViewById(R.id.title);
            textView.setText("page " + (i + 1));
            layout.setBackgroundColor(Color.rgb(255 / (i + 1), 255 / (i + 1), 0));
            createList(layout);
            mListContainer.addView(layout);
        }

//        ViewPager
    }

    private void createList(ViewGroup layout) {
        ListView listView = (ListView) layout.findViewById(R.id.list);
        ArrayList<String> datas = new ArrayList<String>();
        for (int i = 0; i < 50; i++) {
            datas.add("name " + i);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.content_list_item, R.id.name, datas);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(OutEventActivity.this, "click item", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
