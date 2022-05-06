package com.tengfei.fairy.hrblogin;

import android.app.Activity;

/**
 * @ Description :三方登陆Utils（微信）
 * @ Author 李腾飞
 * @ Time 2022/5/4   3:57 PM
 * @ Version :
 */
public  class ThirdPartyLoginUtils {

    private Activity activity;
    private String flag;
    private RequestCallback callback;

    public ThirdPartyLoginUtils(Activity activity, String flag, RequestCallback callback) {
        this.activity = activity;
        this.flag = flag;
        this.callback = callback;
    }



    public interface RequestCallback {
        void onSuccess(String code, String result);

        void onFailed(String code, String msg);
    }
}
