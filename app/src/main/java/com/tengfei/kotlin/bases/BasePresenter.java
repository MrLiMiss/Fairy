package com.tengfei.kotlin.bases;



/**
 * Created by wangxiulong on 16/8/11.
 */
public class BasePresenter {
    protected boolean visiability;
//    protected ServiceManager mServiceManager;

    public BasePresenter() {
        visiability = false;
//        mServiceManager = SDMoneyApplication.getInstance().getServiceManager();
    }

    public void onResume() {
        visiability = true;
    }

    public void onPause() {
        visiability = false;
    }
}
