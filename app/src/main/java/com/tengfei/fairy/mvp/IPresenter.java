package com.tengfei.fairy.mvp;

/**
 * @ Description :
 * @ Author 李腾飞
 * @ Time 2020-11-16   16:48
 * @ Version :
 */
public interface IPresenter<V extends IView> {

    //绑定view
    void attachView(V view);

    //解绑view
    void detachView();

    IView getView();

}

