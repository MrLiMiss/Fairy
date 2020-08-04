package com.tengfei.fairy.application;

import android.app.Application;
import android.content.Context;
import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;
import com.tengfei.fairy.BuildConfig;

/**
 * @ Description :全局application
 * @ Author 李腾飞
 * @ Time 2020-08-04   10:30
 * @ Version :
 */
public class FairyApplication extends MultiDexApplication {
        private static Application mApplication;
        private static Context context;

        @Override
        public void onCreate() {
            super.onCreate();
            mApplication = this;
            context = this;
//            RxJavaPlugins.setErrorHandler(new Consumer<Throwable>() {
//                @Override
//                public void accept(Throwable throwable) {
//                    //异常处理
//                    LogUtils.e(FairyApplication.class, "error = " + throwable.getMessage());
//                }
//            });
//        if (!LeakCanary.isInAnalyzerProcess(this)) {
//            LeakCanary.install(this);
//        }
//            initCrashHandler();
//            //梆梆安全 威胁感知
//            initBangBangSafe();

        }

        private void initBangBangSafe() {
            //        //梆梆安全 威胁感知   可获取梆梆sdk采集推送的消息
//        RiskStubAPI.registerService(new CallBack() {
//            @Override
//            public void onResult(Type type, Object result) {
//                JSONObject jsonObject = (JSONObject) result; //强转为json类型数据
//                if(type == Type.UDID){
//                    Log.d("customInterface","udid is = " + jsonObject.toString());
//                    String udid = jsonObject.optString(Type.UDID.toString());
//                }
//                if(type == Type.DEVINFO){
//                    Log.d("customInterface","devinfo is = " + jsonObject.toString());
//                }
//                if(type == Type.APKINFO){
//                    Log.d("customInterface","apkinfo = " + jsonObject.toString());
//                }
//            }
//        }, Type.UDID);


//
//            //UserStrategy类作为Bugly的初始化扩展，可以修改本次初始化Bugly数据的版本、渠道及部分初始化行为
//            CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(context);
//            //...在这里设置strategy的属性，在bugly初始化时传入
//            strategy.setAppChannel(BuildConfig.DEBUG ? "debug" : "release");  //设置渠道
////        CrashReport.setIsDevelopmentDevice(context, BuildConfig.DEBUG);//调试设备设置成“开发设备”。
//            //如果通过UserStrategy设置了版本号和渠道号，则会覆盖“AndroidManifest.xml”里面配置的版本号和渠道。
//            CrashReport.initCrashReport(context, "f97a92c0d1", true, strategy);
//            //梆梆安全   初始化威胁感知
//            RiskStubAPI.initBangcleEverisk(context, Constants.BANGBANG_KEY);
        }

//        private void initCrashHandler() {
//            CrashHandlerUtils.getInstance().init(context);
//        }

        public static Application getApplication() {
            return mApplication;
        }

        public static Context getContext() {
            return context;
        }

        @Override
        protected void attachBaseContext(Context base) {
            super.attachBaseContext(base);
            MultiDex.install(base);
        }
    }
