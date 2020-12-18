package com.tengfei.kotlin.bases;

import android.os.Bundle;


import androidx.annotation.Nullable;

import com.tengfei.fairy.utils.ToastUtil;
import com.tengfei.kotlin.bases.view.IBaseView;


/**
 * 基础类
 */
public abstract class BaseFragmentActivity extends BaseActivity implements IBaseView {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }



    @Override
    public void showLoadingDiaglog(String msg) {
        showLoading();
    }

    @Override
    public void dissmissLoadingDialog() {
        dismissLoading();
    }

    @Override
    public void showToast(String msg) {
        ToastUtil.showMessage(this, msg);
    }
}
