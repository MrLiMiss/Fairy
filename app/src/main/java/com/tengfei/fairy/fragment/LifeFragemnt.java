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
    @OnClick({})
    void click(View view) {
        switch (view.getId()) {
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
