package com.tengfei.fairy.manager;

import com.tengfei.fairy.mvp.BasePresenter;
import com.tengfei.fairy.mvp.IModel;

/**
 * @ Description :此功能无需数据请求  无model
 * @ Author 李腾飞
 * @ Time 2021/7/14   13:55
 * @ Version :
 */
public class ManagerTestPresenter extends BasePresenter {


    @Override
    protected IModel loadModel() {
        return null;
    }

    @Override
    protected void start() {

    }
}
