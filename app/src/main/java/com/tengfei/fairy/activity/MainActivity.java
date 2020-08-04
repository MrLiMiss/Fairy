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

public class MainActivity extends AppCompatActivity {

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


//public void posthttp(){
//    String Url = "http://130.1.10.158:8888/data";
//    String project="default";
//    String token="1qweasd!";
//    try {
//        // 创建json对象
//        JSONObject jsonObject = new JSONObject();
//        // 1个数组参数
//        JSONArray jsonArray = new JSONArray();
////        for (String tag : tags) {
////            jsonArray.put(tag);
////        }
//        jsonObject.put("tags", jsonArray);
//        // 3个字符串参数
//        jsonObject.put("project", project);
//        jsonObject.put("token", token);
//        String data = jsonObject.toString();
//        Log.e("ltf123", data);
//        // retrofit方式
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(Url)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        Route.ModulesList modulesList = retrofit.create(Route.ModulesList.class);
//        RequestBody requestBody = RequestBody.create(okhttp3.MediaType.parse("application/json;charset=UTF-8"), data);
//        Call<DataInfoModel> moduleBeanCall = modulesList.getModulesList(requestBody);
//        moduleBeanCall.enqueue(new Callback<ModuleBean>() {
//            @Override
//            public void onResponse(Call<ModuleBean> call, Response<ModuleBean> response) {
//            }
//
//            @Override
//            public void onFailure(Call<ModuleBean> call, Throwable t) {
//            }
//        });
//    } catch (JSONException e) {
//        e.printStackTrace();
//    }
//
//}




//    public void text(){
//        Log.e("ltf","text");
//        new Thread(new Runnable() {
//
//            @Override
//            public void run() {
//                // TODO Auto-generated method stub
//                // 定义待请求的URL
//                String Url = "http://130.1.10.158:8888/data";
//                String project="default";
//                String token="1qweasd!";
////                String requestUrl="http://130.1.10.158:8888/data?project=default&token=1qweasd!";
//                String requestUrl="http://130.1.10.158:8888/";
//                JSONArray jsonArray=new JSONArray();
//
////                [{
////                    "is_login_id":true,
////                            "distinct_id":"xukun.cai",
////                            "time":1585455825547,
////                            "type":"track",
////                            "event_code":"APP_START_001",
////
////                            "properties":{
////                        "_ip":"158.11.22.127",
////                                "_os":"mac",
////                                "_brower":"chrome",
////                                "_country":"中国",
////                                "_province":"北京",
////                                "_city":"北京市",
////
////
////                                "_lib":"ios",
////                                "_lib_version":"1.0"
////                    },
////
////                    "lib":{
////                        "_app_version":"1.0.1",
////                                "_lib":"ios",
////                                "_lib_version":"1.0",
////                                "_lib_method":"code",
////
////
////                                "_lib_detail":"POSTMAN##TEST##TEM##1"
////                    }
////                }]
//                try {
//                    JSONObject jsonProperties=new JSONObject();
//                    jsonProperties.put("_ip","158.11.22.127");
//                    jsonProperties.put("_os","android");
//                    jsonProperties.put("_brower","");
//                    jsonProperties.put("_country","中国");
//                    jsonProperties.put("_province","北京");
//                    jsonProperties.put("_city","北京市");
//                    jsonProperties.put("_lib","android");
//                    jsonProperties.put("_lib_version","1.0");
//
//                    JSONObject jsonlib=new JSONObject();
//                    jsonlib.put("_app_version","1.0.1");
//                    jsonlib.put("_lib","android");
//                    jsonlib.put("_lib_version","1.0");
//                    jsonlib.put("_lib_method","code");
//                    jsonlib.put("_lib_detail","POSTMAN##TEST##TEM##1");
//
//                    JSONObject Json = new JSONObject();
//
//                    Json.put("is_login_id", true);
//                    Json.put("distinct_id", "xukun.cai");
//                    Json.put("time", "1585455825547");
//                    Json.put("type", "xukun.cai");
//                    Json.put("event_code", "APP_START_001");
//                    Json.put("properties", jsonProperties);
//                    Json.put("lib", jsonlib);
//                    jsonArray.put(Json);
//
//                    Retrofit retrofit = new Retrofit.Builder()
//                            .baseUrl(requestUrl)
//                            .addConverterFactory(GsonConverterFactory.create())
//                            .build();
//
//                    //获取接口实例
//                    APIService apiService  = retrofit.create(APIService.class);
//                    //调用方法得到一个Call
//                    Call<DataSubject> call = apiService.PostData();
//                    //进行网络请求
//                    call.enqueue(new Callback<DataSubject>() {
//                        @Override
//                        public void onResponse(Call<DataSubject> call, Response<DataSubject> response) {
//                            Log.e("ltf-retrofit2","onResponse");
//                        }
//                        @Override
//                        public void onFailure(Call<DataSubject> call, Throwable t) {
//                            t.printStackTrace();
//                            Log.e("ltf-retrofit2","onFailure");
//                            Log.e("ltf-retrofit2",t.toString());
//                        }
//                    });
//
//
//
//
//
//
//                } catch (Exception e) {
//                    Log.d("TAG", e.toString());
//                }
//
//
//
//            }
//        }).start();
//
//
//    }
}
