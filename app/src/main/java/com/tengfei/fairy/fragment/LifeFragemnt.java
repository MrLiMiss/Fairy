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
 * @ Description :生活fragment
 * @ Author 李腾飞
 * @ Time 2020-09-04   10:53
 * @ Version :
 */
public class LifeFragemnt extends Fragment {


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Logs.i("lifecycle-LifeFragemnt-","setUserVisibleHint():"+isVisibleToUser);
    }


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
        Logs.i("lifecycle-LifeFragemnt-","onCreateView()");
        return inflater.inflate(R.layout.fragment_life, container, false);
    }



    @Override
    public void onActivityCreated(Bundle bundle){
        super.onActivityCreated(bundle);
        Logs.i("lifecycle-LifeFragemnt-","onActivityCreated()");
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        Logs.i("lifecycle-LifeFragemnt-","onStart():");
    }

    @Override
    public void onResume(){
        super.onResume();
        Logs.i("lifecycle-LifeFragemnt-","onResume():");
    }

    @Override
    public void onPause(){
        super.onPause();
        Logs.i("lifecycle-LifeFragemnt-","onPause():");
    }

    @Override
    public void onStop(){
        super.onStop();
        Logs.i("lifecycle-LifeFragemnt-","onStop():");
    }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        Logs.i("lifecycle-LifeFragemnt-","onDestroyView():");

    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Logs.i("lifecycle-LifeFragemnt-","onDestroy():");
    }

    @Override
    public void onDetach(){
        super.onDetach();
        Logs.i("lifecycle-LifeFragemnt-","onDetach():");
    }


}
