package com.tengfei.fairy.mvp;

import androidx.fragment.app.Fragment;

/**
 * @ Description :mvp  fragment 基类
 * @ Author 李腾飞
 * @ Time 2020-11-16   15:06
 * @ Version :
 */
public class BaseMvpFragment <P extends BasePresenter> extends Fragment implements IView {
    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showToast(String msg) {

    }
}
