package com.tengfei.fairy.trymyself;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.tengfei.fairy.R;

/**
 * @ Description :
 * @ Author 李腾飞
 * @ Time 2020-09-03   17:52
 * @ Version :
 */
public class MyActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        initView();
        initData();
    }

    protected void initData() {
//        checkCameraPermission();
    }

    protected void initView() {
        Button button1 = findViewById(R.id.btn1);
        Button button2 = findViewById(R.id.btn2);
        Button button3 = findViewById(R.id.btn3);
        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Log.e("ltf", "onClick");
            }
        });
    }

}
