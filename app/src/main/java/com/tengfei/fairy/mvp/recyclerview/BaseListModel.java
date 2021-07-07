package com.tengfei.fairy.mvp.recyclerview;

import com.tengfei.fairy.mvp.BaseModel;
import com.tengfei.fairy.mvp.BasePresenter;

/**
 * @ Description :recyclerView  model
 * @ Author 李腾飞
 * @ Time 2021/6/29   14:57
 * @ Version :
 */
public abstract class BaseListModel<P extends BasePresenter> extends BaseModel {
    
    public BaseListModel(P presenter) {
        super(presenter);
    }

    public void requestLoadmoreData(int page){
        loadMoreData(page);
    }

    public void requestRefreshData(){
        refreshData();
    }
    protected abstract void refreshData();

    protected abstract void loadMoreData(int page);
}
