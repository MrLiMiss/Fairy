package com.tengfei.fairy.mvp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.tengfei.fairy.base.BaseActivity;
import com.tengfei.fairy.utils.DialogHelper;
import com.tengfei.fairy.utils.IntentUtils;
import com.tengfei.fairy.utils.LogUtils;
import com.tengfei.fairy.utils.ToastUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @ Description :mvp  fragment 基类
 * @ Author 李腾飞
 * @ Time 2020-11-16   15:06
 * @ Version :
 */
public abstract class BaseMvpFragment <P extends BasePresenter> extends Fragment implements IView {
    protected P mPresenter;
    protected Activity mActivity;
    protected View mRootView;
    public Unbinder unbinder;
    private DialogHelper dialogHelper;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        mRootView = View.inflate(mActivity, getLayoutId(), null);
        mPresenter = loadPresenter();
        bindViews(this, mRootView);
        LogUtils.i(getClass(), "fragment_name");
        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initCommonData();
        initView(mRootView);
        initData();
        initListener();
    }

    private void initCommonData() {
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    public DialogHelper getDialog() {
        if (dialogHelper == null) {
            dialogHelper = new DialogHelper(mActivity);
        }

        return dialogHelper;
    }

    public String getResouseString(int resId) {
        return getResources().getString(resId);
    }


    protected abstract P loadPresenter();

    protected abstract void initView(View view);

    protected abstract void initData();

    protected abstract void initListener();

    protected abstract int getLayoutId();

    @Override
    public void showLoading() {
        ((BaseMvpActivity) getActivity()).showLoading();
    }

    @Override
    public void hideLoading() {
        ((BaseMvpActivity) getActivity()).hideLoading();
    }

    @Override
    public void showToast(String msg) {
        ToastUtils.showToast(msg);
    }

    protected void LogI(String log) {
        LogUtils.i(getClass(), log);
    }

    protected void LogD(String log) {
        LogUtils.d(getClass(), log);
    }

    protected void LogE(String log) {
        LogUtils.e(getClass(), log);
    }

    protected void bindViews(Object object, View view) {
        unbinder = ButterKnife.bind(object, view);
    }

    protected void unbindViews() {
        if (unbinder != null) {
            try {
                unbinder.unbind();
            } catch (Exception e) {
                LogE(e.getMessage());
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.clearAllRequest();
            mPresenter.detachView();
        }

        unbindViews();
    }

    @Override
    public void loginAgin() {
//        IntentUtils.toLoginActWithClearTask(mActivity);
    }
}
