package com.tengfei.fairy.mvp.recyclerview;

import com.tengfei.fairy.mvp.IView;

import java.util.List;

/**
 * @ Description :
 * @ Author 李腾飞
 * @ Time 2021/6/29   15:00
 * @ Version :
 */
public interface BaseListIView<T> extends IView {

    /**刷新成功回调
     * @param data
     */
    void refreshSuccess(List<T> data);

    /**上拉加载更多成功
     * @param data
     */
    void loadMoreSuccess(List<T> data);

    /**
     * 下拉刷新 不加载更多
     * @param data
     */
    void refreshSuccessWithNoLoadMore(List<T> data);

    //刷新失败回调
    @Deprecated
    void refreshFailer();

    //获取数据失败回调
    void loadDataFailer(int page, String msg);

    int getEmptyHint();

    String getEmptyHintStr();

    int getEmptyImg();

    void showToast(String msg);

    boolean showDevider();

}
