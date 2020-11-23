package com.tengfei.fairy.photo;

import com.tengfei.fairy.mvp.BasePresenter;
import com.tengfei.fairy.mvp.IModel;
import com.tengfei.fairy.utils.ToastUtils;

/**
 * @ Description :
 * @ Author 李腾飞
 * @ Time 2020-11-23   13:44
 * @ Version :
 */
public class PhotoPresenter extends BasePresenter<PhotoContract.Model,PhotoContract.View> implements PhotoContract.Presenter {
    @Override
    public void getPhoto(String userName, int phototype) {
        getView().showLoading();
        getModel().getPhoto(userName,phototype, new PhotoContract.ResponseCallback<PhotoBean>() {
            @Override
            public void success(PhotoBean response) {
                getView().hideLoading();
               //todo：处理返回数据
            }

            @Override
            public void fail(String s) {

            }

            @Override
            public void error(String error) {

            }
        });
    }


    @Override
    protected PhotoContract.Model loadModel() {
        return new PhotoModel();
    }

    @Override
    protected void start() {
    //初始化方法：
    }
}
