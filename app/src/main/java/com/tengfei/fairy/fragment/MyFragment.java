package com.tengfei.fairy.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.tengfei.fairy.R;
import com.tengfei.fairy.utils.Logs;

/**
 * @ Description :我的Fragment
 * @ Author 李腾飞
 * @ Time 2020-09-04   09:53
 * @ Version :
 */
public class MyFragment extends Fragment {

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Logs.i("lifecycle-MyFragment-","setUserVisibleHint():"+isVisibleToUser);
    }


    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        Logs.i("lifecycle-MyFragment-","onAttach():");
    }

    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        Logs.i("lifecycle-MyFragment-","onCreate():");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Logs.i("lifecycle-MyFragment-","onCreateView()");
        return inflater.inflate(R.layout.fragment_my, container, false);
    }



    @Override
    public void onActivityCreated(Bundle bundle){
        super.onActivityCreated(bundle);
        Logs.i("lifecycle-MyFragment-","onActivityCreated()");
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        Logs.i("lifecycle-MyFragment-","onStart():");
    }

    @Override
    public void onResume(){
        super.onResume();
        Logs.i("lifecycle-MyFragment-","onResume():");
    }

    @Override
    public void onPause(){
        super.onPause();
        Logs.i("lifecycle-MyFragment-","onPause():");
    }

    @Override
    public void onStop(){
        super.onStop();
        Logs.i("lifecycle-MyFragment-","onStop():");
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        Logs.i("lifecycle-MyFragment-","onDestroyView():");

    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Logs.i("lifecycle-MyFragment-","onDestroy():");
    }

    @Override
    public void onDetach(){
        super.onDetach();
        Logs.i("lifecycle-MyFragment-","onDetach():");
    }


}
