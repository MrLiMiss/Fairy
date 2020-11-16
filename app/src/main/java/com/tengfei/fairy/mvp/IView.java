package com.tengfei.fairy.mvp;

/**
 * @ Description : view 接口
 * @ Author 李腾飞
 * @ Time 2020-11-16   16:29
 * @ Version :
 */
public interface IView {
    void showLoading();
    void hideLoading();
    void loginAgin();
    void showToast(String msg);
}
