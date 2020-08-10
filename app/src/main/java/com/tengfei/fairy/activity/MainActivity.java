package com.tengfei.fairy.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.tengfei.fairy.R;
import com.tengfei.fairy.constant.Constants;
import com.tengfei.fairy.utils.DialogHelper;

public class MainActivity extends AppCompatActivity {
    private DialogHelper dialogHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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


    /**
     * 处理权限申请的回调。
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == Constants.Code.PERMISSION_CALL_PHONE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {//允许权限
//                IntentUtils.startoHotLine(mActivity);
                Log.e("onRequestPermissions","允许权限");
            } else {//拒绝权限

                if (!ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, permissions[0])) {
                    //不再提示
                    Log.e("onRequestPermissions","拒绝不再提示");

                } else {//不再提示--deny
//                    ToastTools.showLong(this,"请允许权限");
                }
            }
        }
    }


    @Override     //startActivityforresult(requestcode),跳转到其他界面返回结果（resultCode）进行处理
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case Constants.Code.PERMISSION_SETING_CODE:
//                checkCameraPermission();
                break;
            default:

                break;
        }
    }

    @Override
    public void onBackPressed(){
        getDialog().alert("温馨提示", "您确定要退出app吗？", "确定", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        },"取消", null);
    }

    public DialogHelper getDialog() {
        if (dialogHelper == null) {
            dialogHelper = new DialogHelper(MainActivity.this);
        }

        return dialogHelper;
    }




}
