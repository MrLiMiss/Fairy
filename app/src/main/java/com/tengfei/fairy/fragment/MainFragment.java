package com.tengfei.fairy.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.tengfei.fairy.R;
import com.tengfei.fairy.activity.LoginActivity;
import com.tengfei.fairy.activity.SplashActivity;
import com.tengfei.fairy.utils.Logs;

/**
 * @ Description :主页Fragment
 * @ Author 李腾飞
 * @ Time 2020-09-04   10:53
 * @ Version :
 */
public class MainFragment extends Fragment implements View.OnClickListener {
    private  View contentView;
    public TextView textView;
    public Activity activity;
    public Button button;


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Logs.i("lifecycle-MainFragment-", "setUserVisibleHint():" + isVisibleToUser);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.activity = (Activity) context;
        Logs.i("lifecycle-MainFragment-", "onAttach():");
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Logs.i("lifecycle-MainFragment-", "onCreate():");
        initView();
        initAction();
        initData();
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Logs.i("lifecycle-MainFragment-", "onCreateView()");
        contentView=inflater.inflate(R.layout.fragment_main, container, false);
        button=(Button)contentView.findViewById(R.id.bt_main_login);
        return contentView;
    }


    @Override
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);

        button.setOnClickListener(this);
        Logs.i("lifecycle-MainFragment-", "onActivityCreated()");

    }


    private void initView() {

    }


    private void initAction() {

    }

    private void initData() {
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Logs.i("lifecycle-MainFragment-", "onViewCreated()");
    }

    @Override
    public void onStart() {
        super.onStart();
        Logs.i("lifecycle-MainFragment-", "onStart():");
    }

    @Override
    public void onResume() {
        super.onResume();
        Logs.i("lifecycle-MainFragment-", "onResume():");
    }

    @Override
    public void onPause() {
        super.onPause();
        Logs.i("lifecycle-MainFragment-", "onPause():");
    }

    @Override
    public void onStop() {
        super.onStop();
        Logs.i("lifecycle-MainFragment-", "onStop():");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Logs.i("lifecycle-MainFragment-", "onDestroyView():");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Logs.i("lifecycle-MainFragment-", "onDestroy():");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Logs.i("lifecycle-MainFragment-", "onDetach():");
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.bt_main_login:
                Intent intent2 = new Intent(activity, LoginActivity.class);
                startActivity(intent2);
                break;
             default:
                 break;
        }
    }
}
