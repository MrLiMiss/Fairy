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

import com.bjrxtd.sdk.Touch;
import com.tengfei.fairy.R;
import com.tengfei.fairy.base.BaseFragment;
import com.tengfei.fairy.touch.CommonProperties;
import com.tengfei.fairy.touch.DataUtils;
import com.tengfei.fairy.touch.NetUtils;
import com.tengfei.fairy.touch.TouchData;
import com.tengfei.fairy.utils.IntentUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.internal.Utils;

/**
 * @ Description :Honor Fragment
 * @ Author 李腾飞
 * @ Time 2020-09-04   11:10
 * @ Versi
 */
public class HonorFragment extends BaseFragment {

    @BindView(R.id.btn_myView)
    Button btn_myView;
    @BindView(R.id.btn_viewdrow)
    Button btn_viewdrow;
    @BindView(R.id.btn_EventBus)
    Button btn_EventBus;
    @BindView(R.id.btn_activity)
    Button btn_activity;

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

    private int letf;
    private CommonProperties commonProperties;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.i("View参数-onAttach：", "letf-" + letf);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("View参数-onCreate：", "letf-" + letf);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i("View参数-onCreateView：", "letf-" + letf);

        return super.onCreateView(inflater, container, savedInstanceState);

    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_honor;
    }

    @Override
    protected void initView(View mRootView) {

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @OnClick({R.id.btn_myView,R.id.btn_EventBus,R.id.btn_activity,R.id.test_btn1,R.id.test_btn2,R.id.test_btn3,R.id.test_btn4, R.id.test_btn5})
    void click(View view) {
        switch (view.getId()) {
            case R.id.btn_myView://自定义view
               IntentUtils.toMyViewActivity(getContext());
                break;
//            case R.id.tv_animation:
//                Intent intent=new Intent(getContext(), AnimationActivity.class);
//                break;
//            case R.id.btn_drawablean://帧动画
//                break;
//            case R.id.btn_alphaan://补间动画
//                break;
//            case R.id.btn_rotatean://属性动画
//                break;
//            case R.id.btn_animator4://改变布局参数，实现动画
//                ViewGroup.LayoutParams layoutParams=btn_animator4.getLayoutParams();
//                ViewGroup.MarginLayoutParams marginLayoutParams=(ViewGroup.MarginLayoutParams) btn_animator4.getLayoutParams();
//                 //改变view参数，需为确定参数大小，如为wrap_content 或 match_parent  会出错（默认width等不为真实值）
//                Log.i(" params.width:", layoutParams.width+"");
//                layoutParams.width+=100;
//                Log.i(" params.width:", layoutParams.width+"");
//                marginLayoutParams.leftMargin+=100;
////                btn_myView.requestLayout();
//                btn_animator4.setLayoutParams(layoutParams);
//                btn_animator4.setLayoutParams(marginLayoutParams);
            case R.id.test_btn1://初始化
                Touch touch=  TouchData.getInstance(getContext()).getTouch();
                commonProperties = CommonProperties.getInstance();
                commonProperties.set_app_name("手机银行");
                commonProperties.set_app_version("4.0.2");
                commonProperties.set_carrier(NetUtils.getCellularOperatorType(getContext()));
                commonProperties.set_lib("adnroid");
                commonProperties.set_lib_version("1.0.1");
                commonProperties.set_ip(null);
                commonProperties.set_model(Build.BRAND + ":" + Build.MODEL);
                commonProperties.set_os("android");
                commonProperties.set_os_version(android.os.Build.VERSION.RELEASE);
                commonProperties.set_geo(DataUtils.getLocation(getContext()));
                commonProperties.set_network_type( NetUtils.getNetworkType(getContext()));
                commonProperties.set_device_id("123123123123123");
                TouchData.getInstance(getContext()).trackRegister(commonProperties);
                break;
            case R.id.test_btn2://trackView
                Map<String ,Object > propertiesView=new HashMap<>();
                    TouchData.getInstance(getContext()).trackView("trackViewdistinctId",false,"手机银行_理财","/mobile_bank_business");
                break;
            case R.id.test_btn3://trackClick
                    TouchData.getInstance(getContext()).trackClick("trackClickdintinctied",false,"点击元素1","url_target_click","手机银行——首页","mobile_bank_home");
                break;
            case R.id.test_btn4://trackEvent
                 TouchData.getInstance(getContext()).trackEvent("MD5123123123start",false,"_AppStart");
                break;
            case R.id.test_btn5://trackSignUp
                TouchData.getInstance(getContext()).trackSiginUp("MD5123123123123123","123anonymousId");
                break;
            case R.id.btn_EventBus:
                IntentUtils.toEventBusActivity(getContext());
                break;
            case R.id.btn_activity:
                IntentUtils.toConfigChenagedActivity(getContext());
                break;
            default:
                break;

        }
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }


    @Override
    public void onStart() {
        super.onStart();
        letf = btn_myView.getLeft();
        Log.i("View参数-onStart：", "letf-" + letf);
    }

    @Override
    public void onResume() {
        super.onResume();
        letf = btn_myView.getLeft();
        Log.i("View参数-onResume：", "letf-" + letf);
    }

    @Override
    public void onPause() {
        super.onPause();
        letf = btn_myView.getLeft();
        Log.i("View参数-onPause：", "letf-" + letf);
    }

    @Override
    public void onStop() {
        super.onStop();
        letf = btn_myView.getLeft();
        Log.i("View参数-onStop：", "letf-" + letf);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        letf = btn_myView.getLeft();
        Log.i("View参数-onDestroyView：", "letf-" + letf);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        letf = btn_myView.getLeft();
        Log.i("View参数-onDestroy：", "letf-" + letf);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        letf = btn_myView.getLeft();
        Log.i("View参数-onDetach：", "letf-" + letf);
    }
}
