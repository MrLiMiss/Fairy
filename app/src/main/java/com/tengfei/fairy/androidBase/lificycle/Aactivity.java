package com.tengfei.fairy.androidBase.lificycle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.tengfei.fairy.R;

/**
 * @ Description :生命周期跳转测试 activity A
 * @ Author 李腾飞
 * @ Time 2021/2/20   10:33
 * @ Version :
 */
public class Aactivity extends Activity  implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);
        Log.d("aclife","A-onCreate");
    }

    @Override
    public void onStart(){
        super.onStart();
        Button button=findViewById(R.id.btn_a);
        button.setOnClickListener(this);
        Log.d("aclife","A-onStart");
    }

    @Override
    public void onRestart(){
        super.onRestart();
        Log.d("aclife","A-onRestart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("aclife","A-onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("aclife","A-onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("aclife","A-onStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("aclife","A-onDestroy");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("aclife","A-onSaveInstanceState");
    }

    @Override
    public void onRestoreInstanceState(Bundle outState) {
        super.onRestoreInstanceState(outState);
        Log.d("aclife","A-onRestoreInstanceState");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_a:
                Intent intent=new Intent(this,Bactivity.class);
                this.startActivity(intent);
                break;
            default:
                break;

        }
    }
}
