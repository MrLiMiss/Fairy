package com.tengfei.fairy.mvp.recyclerview;

import com.tengfei.fairy.mvp.BasePresenter;

/**
 * @ Description :recyclerView Presenter
 * @ Author 李腾飞
 * @ Time 2021/6/29   14:55
 * @ Version :
 */
public abstract class BaseListPresenter<M extends BaseListModel,V extends BaseListIView> extends BasePresenter<M,V> {

    public void refresh() {
        if (null == getView()) {
            return;
        }
        getModel().requestRefreshData();
    }

}
