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
                Log.e("ltf","onClick");
//                HrbbData.trackEvent("_appStart");

//                HrbbData.trackView("首页","www.mainActivity.com");
//                HrbbData.trackClick("按钮1","www.button.com","MainActivity","www.mainactivity.com");
//                HrbbData.encryptionEvent(MainActivity.this,"APP_START_04","title4","url4");
            }

        });
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
//             ackEvent("event_code20");
            }

        });
        button3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
//                Constants.trackView="003";
//                String trackView=Constants.trackView;


//                ToastTools.showLong(MainActivity.this,content+"");
            }

        });
    }



//    private void checkCameraPermission() {
//        String[] reqPermissions = {Manifest.permission.ACCESS_FINE_LOCATION,
//                Manifest.permission.ACCESS_COARSE_LOCATION,
//                Manifest.permission.READ_EXTERNAL_STORAGE,
//                Manifest.permission.WRITE_EXTERNAL_STORAGE,
//                Manifest.permission.READ_PHONE_STATE,
//                Manifest.permission.GET_TASKS};
//        AndPermission.with(MainActivity.this)
//                //.runtime()
//                .permission(reqPermissions)
//                //.onGranted(action)
//                .onGranted(new Action() {
//                    @Override
//                    public void onAction(Object data) {
//                        Log.e("AndPermission", "权限均允许");
//                    }
//
//                })
//
//                .onDenied(new Action() {
//                    @Override
//                    public void onAction(Object data) {
//                        // Storage permission are not allowed.
//                        //* 判断用户是否点击了禁止后不再询问
//                        if (AndPermission.hasAlwaysDeniedPermission(MainActivity.this, reqPermissions)) {
//                            Log.e("AndPermission", "部分功能被禁止");
//                            //System.exit(0) ;//直接退出
//                        }
//                    }
//
//                })
//                .start();
//
//
//
//        int hasCameraPermission = ContextCompat.checkSelfPermission(getApplication(),
//                Manifest.permission.ACCESS_COARSE_LOCATION);
//        if (hasCameraPermission == PackageManager.PERMISSION_GRANTED) {
//            //有权限
////            IntentUtils.startoHotLine(mActivity);
//        } else {
//            //没有权限，申请权限。
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
//                    com.tengfei.fairy.constant.Code.PERMISSION_LOCATION);
//        }
//    }

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
                finish();
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
