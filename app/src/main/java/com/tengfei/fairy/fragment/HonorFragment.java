package com.tengfei.fairy.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.tengfei.fairy.R;
import com.tengfei.fairy.animation.AnimationActivity;
import com.tengfei.fairy.base.BaseFragment;
import com.tengfei.fairy.utils.IntentUtils;

import butterknife.BindView;
import butterknife.OnClick;

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
//        letf = btn_myView.getLeft();
//        int top = btn_myView.getTop();
//        int right = btn_myView.getRight();
//        int buttom = btn_myView.getBottom();
//        float translationX = btn_myView.getTranslationX();
//        float translationY = btn_myView.getTranslationY();
//        Log.i("View参数-initView：", "letf-" + letf);
//        Log.i("View参数：", "top-" + top);
//        Log.i("View参数：", "right-" + right);
//        Log.i("View参数：", "buttom-" + buttom);
//        Log.i("View参数：", "translationX-" + translationX);
//        Log.i("View参数：", "translationY-" + translationY);

    }

    @OnClick({R.id.btn_myView,R.id.btn_EventBus,R.id.btn_activity})
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
