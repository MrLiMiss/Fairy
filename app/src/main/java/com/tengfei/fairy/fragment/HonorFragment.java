package com.tengfei.fairy.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.tengfei.fairy.R;
import com.tengfei.fairy.utils.Logs;

/**
 * @ Description :Honor Fragment
 * @ Author 李腾飞
 * @ Time 2020-09-04   11:10
 * @ Version :
 */
public class HonorFragment extends Fragment {



    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Logs.i("lifecycle-HonorFragment-","setUserVisibleHint():"+isVisibleToUser);
    }


    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        Logs.i("lifecycle-HonorFragment-","onAttach():");
    }

    @Override
    public void onCreate(Bundle bundle){
        super.onCreate(bundle);
        Logs.i("lifecycle-HonorFragment-","onCreate():");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Logs.i("lifecycle-HonorFragment-","onCreateView()");
        return inflater.inflate(R.layout.fragment_honor, container, false);
    }



    @Override
    public void onActivityCreated(Bundle bundle){
        super.onActivityCreated(bundle);
        Logs.i("lifecycle-HonorFragment-","onActivityCreated()");
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Logs.i("lifecycle-HonorFragment-","onViewCreated()");
    }

    @Override
    public void onStart() {
        super.onStart();
        Logs.i("lifecycle-HonorFragment-","onStart():");
    }

    @Override
    public void onResume(){
        super.onResume();
        Logs.i("lifecycle-HonorFragment-","onResume():");
    }

    @Override
    public void onPause(){
        super.onPause();
        Logs.i("lifecycle-HonorFragment-","onPause():");
    }

    @Override
    public void onStop(){
        super.onStop();
        Logs.i("lifecycle-HonorFragment-","onStop():");
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        Logs.i("lifecycle-HonorFragment-","onDestroyView():");

    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Logs.i("lifecycle-HonorFragment-","onDestroy():");
    }

    @Override
    public void onDetach(){
        super.onDetach();
        Logs.i("lifecycle-HonorFragment-","onDetach():");
    }


}
