package com.tengfei.fairy.androidBase.lificycle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.tengfei.fairy.R;

/**
 * @ Description :生命周期跳转测试 activity B
 * @ Author 李腾飞
 * @ Time 2021/2/20   10:34
 * @ Version :
 */
public class Bactivity extends Activity implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);
        Log.d("aclife","B-onCreate");
    }

    @Override
    public void onStart(){
        super.onStart();
        Button button=findViewById(R.id.btn_b);
        Button button2=findViewById(R.id.btn_bb);
        button.setOnClickListener(this);
        button2.setOnClickListener(this);
        Log.d("aclife","B-onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("aclife","B-onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("aclife","B-onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("aclife","B-onStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("aclife","B-onDestroy");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("aclife","B-onSaveInstanceState");
    }

    @Override
    public void onRestoreInstanceState(Bundle outState) {
        super.onRestoreInstanceState(outState);
        Log.d("aclife","B-onRestoreInstanceState");
    }
    @Override
    public void onNewIntent(Intent intent){
        super.onNewIntent(intent);
        Log.d("aclife","B-onNewIntent");

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_b://B activity 跳转A activity
                Intent intent=new Intent(this,Aactivity.class);
                this.startActivity(intent);
                break;
            case R.id.btn_bb://Bactivity 跳转自身
                Intent intent1=new Intent(this,Bactivity.class);
                startActivity(intent1);
            default:
                break;

        }


    }
}
