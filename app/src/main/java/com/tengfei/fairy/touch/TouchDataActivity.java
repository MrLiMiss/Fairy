package com.tengfei.fairy.touch;

import android.os.Build;
import android.view.View;

import com.bjrxtd.sdk.Touch;
import com.tengfei.fairy.R;
import com.tengfei.fairy.base.BaseActivity;

import butterknife.OnClick;

import static com.tengfei.fairy.application.FairyApplication.getContext;

/**
 * @ Description :Touch相关测试Activity
 * @ Author 李腾飞
 * @ Time 2021/7/16   14:37
 * @ Version :
 */
public class TouchDataActivity extends BaseActivity {
    @Override
    protected int getContentLayout() {
        return R.layout.activity_touch;
    }

    @OnClick({R.id.test_btn1,R.id.test_btn2,R.id.test_btn3,R.id.test_btn4})
    void click(View view){
        switch (view.getId()){
            case R.id.test_btn1://初始化
                touchInit();
                break;
            case R.id.test_btn2://trackView
//                Map<String ,Object > propertiesView=new HashMap<>();
//                    TouchData.trackView("honor-trackViewdistinctId",false,"手机银行_理财","/mobile_bank_business");
                break;
            case R.id.test_btn3://trackClick
                TouchData.trackClick("honor-trackClickdintinctied",false,"点击元素1","url_target_click","手机银行——首页","mobile_bank_home");
                break;
            case R.id.test_btn4://trackEvent
                TouchData.trackEvent("honor-MD5123123123start",false,"_AppStart");
                break;
        }
    }

    @Override
    protected void initGui() {

    }

    @Override
    protected void initAction() {

    }

    @Override
    protected void initData() {

    }

    /**
     * 初始化埋点
     */
    private void touchInit() {
        CommonProperties commonProperties;
        String _geo=DataUtils.getLocation(getContext());
        Touch touch=  TouchData.getInstance(getContext().getApplicationContext()).getTouch();

        commonProperties = CommonProperties.getInstance();
        commonProperties.set_app_name("手机银行");
        commonProperties.set_app_version("4.0.2");
        commonProperties.set_carrier(NetUtils.getCellularOperatorType(getContext()));
        commonProperties.set_lib("adnroid");
        commonProperties.set_lib_version("1.0.1");
//                commonProperties.set_ip(null);
        commonProperties.set_model(Build.BRAND + ":" + Build.MODEL);
        commonProperties.set_os("android");
        commonProperties.set_os_version(android.os.Build.VERSION.RELEASE);
        commonProperties.set_geo(_geo);
//                commonProperties.set_network_type( NetUtils.getNetworkType(getContext()));
        commonProperties.set_device_id("123123123123123");
//                TouchData.trackRegister(commonProperties);
        TouchData.getInstance(getContext().getApplicationContext()).trackInit(commonProperties);
    }
}
