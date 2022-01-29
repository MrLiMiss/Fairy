package com.tengfei.fairy.fragment;

import android.content.Context;
import android.content.Intent;
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
import com.tengfei.fairy.utils.FileUtil;
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

    @BindView(R.id.btn_viewdrow)
    Button btn_viewdrow;


    private int letf;


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
    @OnClick({R.id.btn_touch,R.id.tv_base_android,R.id.tv_widget,R.id.tv_triparty,R.id.btn_activity,R.id.test_test,R.id.btn_service,R.id.btn_manager,R.id.btn_muti_thread,R.id.tv_http,R.id.btn_jni})
    void click(View view) {
        switch (view.getId()) {
            case R.id.tv_base_android://Android 基础知识
                IntentUtils.toKnowledgeActivity(getContext());
                break;
            case R.id.tv_widget://自定义组件
                IntentUtils.toWidgetActivity(getContext());
                break;
            case R.id.tv_triparty:
                IntentUtils.toTripartyActivity(getContext());
                break;
            case R.id.btn_touch:
                IntentUtils.toTouchActivity(getContext());
                break;
            case R.id.test_test:
                Context mContext=getContext();
                FileUtil.test(mContext);
                FileUtil.getCachePath(mContext);
                FileUtil.testExternal(mContext);
                break;
            case R.id.btn_service://service测试
                IntentUtils.toServiceActivity(getContext());
                break;
            case R.id.btn_manager://系统manager 测试
                IntentUtils.toManagerActivity(getContext());
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
            case R.id.tv_http:
                IntentUtils.toSocketActivity(getContext());
                break;
            case R.id.btn_activity://activity相关测试
                IntentUtils.toAboutActivity(getContext());
//                IntentUtils.toConfigChenagedActivity(getContext());
                break;
            case R.id.btn_muti_thread://多线程
                IntentUtils.toMutiThread(getContext());
                break;
            case R.id.btn_jni:
                IntentUtils.toJniActivity(getContext());
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
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
