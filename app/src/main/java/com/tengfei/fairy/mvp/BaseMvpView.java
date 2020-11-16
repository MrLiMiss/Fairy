package com.tengfei.fairy.mvp;

import android.content.Context;

/**
 * @ Description :mvp view基类
 * @ Author 李腾飞
 * @ Time 2020-11-16   15:08
 * @ Version :
 */
public interface BaseMvpView {

    void showToast(String error);

    void showToastCenter(String msg);
    void showLoadingView();

    void dismisLoadingView();

    void exitAct();

    void showTipsDialog(String code,String msg);

    void showTipsDialog(String msg);

    Context getContext();
}
