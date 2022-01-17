package com.tengfei.fairy.manager;


import android.os.Build;
import android.view.View;

import androidx.annotation.RequiresApi;

import com.tengfei.fairy.R;
import com.tengfei.fairy.mvp.BaseMvpActivity;
import com.tengfei.fairy.widget.ActionBarView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ Description :系统manager 测试
 * @ Author 李腾飞
 * @ Time 2021/7/14   13:51
 * @ Version :
 */
public class ManagerTestActivity extends BaseMvpActivity<ManagerTestPresenter> implements IManagerView {
    @BindView(R.id.view_actionbar)
    ActionBarView actionBarView ;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_manager;
    }

    @Override
    protected ManagerTestPresenter loadPresenter() {
        return null;//无数据请求  无需presenter处理
    }

    @Override
    protected void initView() {

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @OnClick({R.id.view_actionbar})
    void click(View view){
       switch(view.getId()){
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
}
