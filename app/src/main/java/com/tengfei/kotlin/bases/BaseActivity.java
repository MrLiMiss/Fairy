package com.tengfei.kotlin.bases;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.tengfei.fairy.utils.LogUtils;
import com.tengfei.kotlin.bases.token.TokenEvent;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseActivity extends AppCompatActivity{
    private Unbinder unbinder;

//    protected ServiceManager mServiceManager;
//    protected TokenService mTokenService;
//    private CommonLoadingDialog loadingDialog;
    public FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//        mServiceManager = SDMoneyApplication.getInstance().getServiceManager();
//        mTokenService = mServiceManager.getTokenService();
//        StatusBarUtil.setStatusBar(this, 0);
//        if (StatusBarUtil.isMIUI() && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            //设置statusbar字体高亮
//            StatusBarUtil.setXiaomiStatusBar(getWindow(), true);
//        }
    }

    protected void bindViews(Activity activity) {
        unbinder = ButterKnife.bind(activity);
    }

    @Override
    protected void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onPause() {
        EventBus.getDefault().unregister(this);
        super.onPause();
    }

    protected void unbindViews() {
        try {
            unbinder.unbind();
        } catch (Exception e) {
            LogUtils.e("TAG",e.getMessage().toString());
        }
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() == 1) {
            finish();
        } else {
            super.onBackPressed();
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

    protected void showLoading() {
//        if (loadingDialog != null) {
//            if (loadingDialog.isShowing()) {
//                loadingDialog.dismiss();
//            }
//        } else {
//            loadingDialog = new CommonLoadingDialog.Builder(this).build();
//        }
//        loadingDialog.show();
    }

    protected void dismissLoading() {
//        if (loadingDialog != null && loadingDialog.isShowing()) {
//            loadingDialog.dismiss();
//        }
    }

    /**
     * 显示软键盘
     *
     * @param edit
     */
    public void showSoftInput(final EditText edit) {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        edit.requestFocus();
        imm.showSoftInput(edit, InputMethodManager.SHOW_FORCED);
    }

    /**
     * 隐藏软键盘
     */
    public void hideSoftInput() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        View view = getCurrentFocus();
        if (view != null) {
            view.clearFocus();
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onTokenEvent(TokenEvent event) {
//        try {
//            UserInfoCache.getCache().loginOut();
//            mServiceManager.getTokenService().refreshAccessToken("");
//            SharedPrefUtil.clear(null);
//            PushManager.getInstance().turnOffPush(getApplicationContext());
//            NotificationManager nm = (NotificationManager) getParent().getSystemService(Context.NOTIFICATION_SERVICE);
//            nm.cancelAll();
//        } catch (Exception e) {
//
//        }

//        if (event.getCode() == Constants.error.ERROR_TOKEN_UNAVAILABLE) {
//            showOtherLogin(event.getMsg());
//        } else {
//            Intent intent = new Intent(this, LoginActivity.class);
//            startActivityForResult(intent, 0);
//        }
    }

    private void showOtherLogin(String msg) {
//        CommonDialog.Builder commonDialog = new CommonDialog.Builder(this);
//        commonDialog.addBankLimit();
//        commonDialog.setMessage(msg);
//        commonDialog.setSingleButton(getString(R.string.sdf_common_ok), new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
//                startActivityForResult(intent, 0);
//            }
//        }).build().show();
    }



    public void addFragment(int containerId, BaseFragment to, String tag) {
        if (fm == null) {
            fm = getSupportFragmentManager();
        }
        try {
            FragmentTransaction ft = fm.beginTransaction()
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
//            LogUtil.e("添加fragment出错.");
        } finally {
        }
    }

    public void popFragment() {
        if (fm == null) {
            fm = getSupportFragmentManager();
        }
        int count = fm.getBackStackEntryCount();//获取Fragment堆栈内 Fragment的数量
        if (count > 1) {
            fm.popBackStackImmediate();//Fragment堆栈 弹栈
        } else {
            finish();
        }
    }

    public BaseFragment getTopFragment() {
        if (fm == null) {
            fm = getSupportFragmentManager();
        }
        int count = fm.getBackStackEntryCount();
        if (count > 0) {
            String tag = fm.getBackStackEntryAt(count - 1).getName();
            BaseFragment fragment = (BaseFragment) fm.findFragmentByTag(tag);
            return fragment;

        }
        return null;
    }
}
