package com.tengfei.fairy.mvp;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.tengfei.fairy.base.BaseFragment;
import com.tengfei.fairy.config.Constants;
import com.tengfei.fairy.utils.AppUtils;
import com.tengfei.fairy.utils.DialogHelper;
import com.tengfei.fairy.utils.LogUtils;
import com.tengfei.fairy.utils.ToastUtils;
import com.tengfei.fairy.widget.Dialog.CustomLoadingDialog;
import com.tengfei.fairy.widget.LeakmLastSrvView;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @ Description :mvp activity 基类
 * @ Author 李腾飞
 * @ Time 2020-11-16   15:07
 * @ Version :
 */
public abstract class BaseMvpActivity<P extends BasePresenter> extends FragmentActivity implements IView {
    protected P mPresenter;
    public Unbinder unbinder;
    public Activity mActivity;
    protected View mRootView;
    private DialogHelper dialogHelper;
    private CustomLoadingDialog loadingDialog;
    protected FragmentManager mFm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //禁止横屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //设置软键盘输入类型，防止遮挡屏幕
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        mRootView = View.inflate(this, getLayoutId(), null);
        setContentView(mRootView);
        setStatusBar(0);
        bindViews(this);
        this.mActivity = this;
        //获取实例
        mPresenter = loadPresenter();
        initCommonData();
        initView();
        initData();
        initListener();

    }

    /**
     * 返回layoutId
     *
     * @return
     */
    protected abstract int getLayoutId();

    private void initCommonData() {
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    protected abstract P loadPresenter();

    protected abstract void initView();

    protected abstract void initData();

    protected abstract void initListener();

    @Override
    public void showLoading() {

        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (loadingDialog == null) {
                    loadingDialog = new CustomLoadingDialog.Builder(mActivity).build();
                }

                if (!loadingDialog.isShowing()) {
                    loadingDialog.show();
                }
            }
        });

    }

    @Override
    public void hideLoading() {

        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
    }

    /**
     * 设置状态栏
     */
    public void setStatusBar(int color) {

        Window window = this.getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (color == 0) {
                window.setStatusBarColor(Color.WHITE);
            } else {
                window.setStatusBarColor(ContextCompat.getColor(this, color));
            }
            //6.0以上状态栏文字颜色设置深色
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(Color.LTGRAY);
        }
    }

    /**
     * 设置Activity全屏显示
     * 状态栏透明
     */
    public void initWindow() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(0x00000000);
        }
    }


    public void setStatusColor(boolean isGray) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View decorView = getWindow().getDecorView();
            if (decorView != null) {
                int vis = decorView.getSystemUiVisibility();
                if (isGray) {
                    //灰色
                    vis |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
                } else {
                    //白色
                    vis &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
                }
                decorView.setSystemUiVisibility(vis);
            }
        }
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

    public void bindViews(Activity activity) {
        unbinder = ButterKnife.bind(activity);
    }

    public void unbindViews() {
        try {
            unbinder.unbind();
        } catch (Exception e) {
            LogE(e.getMessage().toString());
        }
    }

    public String getResouseString(int resId) {
        return getResources().getString(resId);
    }

    public DialogHelper getDialog() {
        if (dialogHelper == null) {
            dialogHelper = new DialogHelper(mActivity);
        }

        return dialogHelper;
    }

    public void addFragment(int containerId, BaseFragment to, String tag) {
        if (mFm == null) {
            mFm = getSupportFragmentManager();
        }
        try {
            FragmentTransaction ft = mFm.beginTransaction()
                    .setTransition(FragmentTransaction.TRANSIT_NONE)
                    .add(containerId, to, tag);

            if (getTopFragment() != null) {
                ft.hide(getTopFragment());

            } else {
                Bundle bundle = to.getArguments();
                if (bundle == null) {
                    bundle = new Bundle();
                }
                bundle.putBoolean("root_flag", true);
                to.setArguments(bundle);
            }
            ft.addToBackStack(tag);
            ft.commit();
        } catch (Exception e) {
            LogE("添加fragment出错.");
        } finally {
        }
    }

    public BaseFragment getTopFragment() {
        if (mFm == null) {
            mFm = getSupportFragmentManager();
        }
        int count = mFm.getBackStackEntryCount();
        if (count > 0) {
            String tag = mFm.getBackStackEntryAt(count - 1).getName();
            BaseFragment fragment = (BaseFragment) mFm.findFragmentByTag(tag);
            return fragment;

        }
        return null;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.clearAllRequest();
            mPresenter.detachView();
        }
        unbindViews();
        //华为设备mLastSrvView引起的内存泄漏
        if (AppUtils.isDevice(Constants.Device.HUAWEI)) {
            LeakmLastSrvView.fixLeak(mActivity);
        }
    }

    @Override
    public void loginAgin() {
//        IntentUtils.toLoginActWithClearTask(mActivity);
    }


}
