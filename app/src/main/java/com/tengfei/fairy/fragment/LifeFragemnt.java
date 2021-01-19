package com.tengfei.fairy.fragment;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.bjrxtd.sdk.Touch;
import com.tengfei.fairy.R;
import com.tengfei.fairy.base.BaseFragment;
import com.tengfei.fairy.touch.CommonProperties;
import com.tengfei.fairy.touch.TouchData;
import com.tengfei.fairy.utils.IntentUtils;
import com.tengfei.fairy.utils.Logs;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ Description :生活fragment
 * @ Author 李腾飞
 * @ Time 2020-09-04   10:53
 * @ Version :
 */
public class LifeFragemnt extends BaseFragment {

    @BindView(R.id.test_btn1)
    Button btnInit;
    @BindView(R.id.test_btn2)
    Button btnTrackView;
    @BindView(R.id.test_btn3)
    Button btnTrackClick;
    @BindView(R.id.test_btn4)
    Button btnTrackEvent;
    @BindView(R.id.test_btn5)
    Button btnSignUp;

    private CommonProperties commonProperties;

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        Logs.i("lifecycle-LifeFragemnt-","onAttach():");
    }

    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        Logs.i("lifecycle-LifeFragemnt-","onCreate():");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return super.onCreateView(inflater, container, savedInstanceState);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_life;
    }

    @Override
    protected void initView(View mRootView) {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @OnClick({R.id.test_btn1,R.id.test_btn2,R.id.test_btn3,R.id.test_btn4, R.id.test_btn5})
    void click(View view) {
        switch (view.getId()) {
            case R.id.test_btn1://初始化
                Touch touch=  TouchData.getInstance(getContext()).getTouch();
                commonProperties = CommonProperties.getInstance();
                commonProperties.set_app_name("手机银行");
                commonProperties.set_app_version("4.0.2");
                commonProperties.set_carrier("中国联通");
                commonProperties.set_lib("adnroid");
                commonProperties.set_lib_version("1.0.1");
                commonProperties.set_ip(null);
                commonProperties.set_model("huawei mate8");
                commonProperties.set_os("android");
                commonProperties.set_os_version("8.0.0");
                commonProperties.set_geo("116.48927,39.89225");
                commonProperties.set_network_type("wifi");
                commonProperties.set_device_id("123123123123123");
                TouchData.trackRegister(commonProperties);
                break;
            case R.id.test_btn2://trackView
                Map<String ,Object > propertiesView=new HashMap<>();
                TouchData.trackView("life-trackViewdistinctId",false,"手机银行_理财","/mobile_bank_business");
                break;
            case R.id.test_btn3://trackClick
                TouchData.trackClick("life-trackContradistinction",false,"点击元素1","url_target_click","手机银行——首页","mobile_bank_home");
                break;
            case R.id.test_btn4://trackEvent
                TouchData.trackEvent("life-MD5123123123start",false,"_AppStart");
                break;
            case R.id.test_btn5://trackSignUp
                TouchData.trackSiginUp("life-MD5123123123123123","123anonymousId");
                break;
            default:
                break;

        }
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Logs.i("lifecycle-LifeFragemnt-","setUserVisibleHint():"+isVisibleToUser);
    }








}
