package com.tengfei.fairy.mvp;

import com.tengfei.fairy.config.Constants;
import com.tengfei.fairy.utils.SPUtils;

import java.lang.ref.WeakReference;

/**
 * @ Description : mvp Presenter 基类
 * @ Author 李腾飞
 * @ Time 2020-11-16   15:07
 * @ Version :
 */
public abstract class BasePresenter <M extends IModel, V extends IView> implements IPresenter{
    protected WeakReference weakReference;
    protected M iModel;
    //protected V iView;

    public BasePresenter() {
        iModel = loadModel();
    }

    @Override
    public void attachView(IView view) {
        weakReference = new WeakReference(view);
    }

    @Override
    public void detachView() {
        if (weakReference != null) {
            weakReference.clear();
            weakReference = null;
        }
    }

    @Override
    public V getView() {
        if(weakReference == null){
            return null;
        }
        return (V) weakReference.get();
    }

    protected M getModel() {
        //使用前先进行初始化
        return iModel;
    }

    protected abstract M loadModel();

    /**
     * 初始化方法
     */
    protected abstract void start();

    public void showLoading() {
        if (getView() != null) {
            getView().showLoading();
        }
    }

    public void hideLoading() {
        if (getView() != null) {
            getView().hideLoading();
        }
    }



    public void clearAllRequest() {
        if (null != getModel()) {
            getModel().clearAllCall();
        }
    }



    /**
     * 重新登录
     */
    public void loginAgin(){
        if(getView() != null){
            getView().loginAgin();
            SPUtils.put(Constants.SPKey.TOKEN,"");
        }
    }

}
