package com.tengfei.fairy.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.tengfei.fairy.R;
import com.tengfei.fairy.adapter.ViewPagerAdapter;
import com.tengfei.fairy.base.BaseActivity;
import com.tengfei.fairy.config.Constants;
import com.tengfei.fairy.fragment.HonorFragment;
import com.tengfei.fairy.fragment.LifeFragemnt;
import com.tengfei.fairy.fragment.MainFragment;
import com.tengfei.fairy.fragment.MyFragment;
import com.tengfei.fairy.utils.DialogHelper;
import com.tengfei.fairy.widget.TabButton;
import com.yanzhenjie.permission.AndPermission;

public class MainActivity extends BaseActivity {
    ViewPager mViewPager;

    TabLayout mTabLayout;
    ViewPagerAdapter mViewPagerAdapter;

    private DialogHelper dialogHelper;

    int prePosition = 0;
    private final int[] TAB_IMGS = new int[]{R.drawable.selector_tab_home, R.drawable.selector_tab_invest, R.drawable.selector_tab_life, R.drawable.selector_tab_my};
    private String[] TAB_TITLES = {"主页", "荣耀", "生活", "我的"};
    private Class[] TAB_FRAGMENT = {MainFragment.class, HonorFragment.class, LifeFragemnt.class, MyFragment.class};

    @Override
    protected int getContentLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initGui() {
//        initWindow();
        mTabLayout = findViewById(R.id.tabLayout);
        mViewPager = findViewById(R.id.viewpager);
        initFragment(TAB_FRAGMENT, TAB_IMGS, TAB_TITLES);
        initWindow();

    }

    private void initFragment(Class[] fragments, int[] imgs, String[] titles) {
        mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        setTabs(mTabLayout, this.getLayoutInflater(), titles, imgs);
        Fragment fragment;
        for (int i = 0; i < fragments.length; i++) {
            try {
                fragment = (Fragment) fragments[i].newInstance();
                mViewPagerAdapter.addFragment(fragment, titles[i]);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        mTabLayout.addOnTabSelectedListener(onTabSelectedListener);
//        mViewPager.setPagingEnabled(false);
        mViewPager.setAdapter(mViewPagerAdapter);
        mViewPager.setOffscreenPageLimit(TAB_FRAGMENT.length);
        indexTab(0);
    }

    public void indexTab(int myPosition) {
        if (mTabLayout != null) {
            TabLayout.Tab tabAt = mTabLayout.getTabAt(myPosition);
            if (tabAt != null) {
                tabAt.select();
            }
        }
    }

    TabLayout.OnTabSelectedListener onTabSelectedListener = new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            mViewPager.setCurrentItem(tab.getPosition(), false);
            prePosition = tab.getPosition();
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {

        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {

        }
    };

    /**
     * 设置添加Tab
     */
    private void setTabs(TabLayout tabLayout, LayoutInflater inflater, String[] tabTitlees, int[] tabImgs) {
        for (int i = 0; i < tabTitlees.length; i++) {
            TabLayout.Tab tab = tabLayout.newTab();
            View view = inflater.inflate(R.layout.layout_tab_custom, null);
            tab.setCustomView(view);
            TabButton tabButton = view.findViewById(R.id.tabButtun);
            tabButton.setButtonTextContent(tabTitlees[i]);
            tabButton.setButtunImageRes(tabImgs[i]);
            tabButton.setTextAlignment(TextView.TEXT_ALIGNMENT_CENTER);
            tabLayout.addTab(tab);
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

    @Override
    protected void initAction() {

    }

    protected void initData() {
        checkCameraPermission();

    }

    private void checkCameraPermission() {
        String[] reqPermissions = {Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.GET_TASKS};
        AndPermission.with(MainActivity.this)
                //.runtime()
                .permission(reqPermissions)
                //.onGranted(action)
                .onGranted(data -> Log.e("AndPermission", "权限均允许"))

                .onDenied(data -> {
                    // Storage permission are not allowed.
                    //* 判断用户是否点击了禁止后不再询问
                    if (AndPermission.hasAlwaysDeniedPermission(MainActivity.this, reqPermissions)) {
                        Log.e("AndPermission", "部分功能被禁止");
                        //System.exit(0) ;//直接退出
                    }
                })
                .start();


        int hasCameraPermission = ContextCompat.checkSelfPermission(getApplication(),
                Manifest.permission.ACCESS_COARSE_LOCATION);
        if (hasCameraPermission == PackageManager.PERMISSION_GRANTED) {
            //有权限
//            IntentUtils.startoHotLine(mActivity);
        } else {
            //没有权限，申请权限。
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                    Constants.Code.PERMISSION_LOCATION);
        }
    }


//    /**
//     * 处理权限申请的回调。
//     */
//    @Override
//    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//        if (requestCode == Constants.Code.PERMISSION_CALL_PHONE) {
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {//允许权限
////                IntentUtils.startoHotLine(mActivity);
//                Log.e("onRequestPermissions", "允许权限");
//            } else {//拒绝权限
//
//                if (!ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, permissions[0])) {
//                    //不再提示
//                    Log.e("onRequestPermissions", "拒绝不再提示");
//
//                } else {//不再提示--deny
////                    ToastTools.showLong(this,"请允许权限");
//                }
//            }
//        }
//    }


    @Override     //startActivityforresult(requestcode),跳转到其他界面返回结果（resultCode）进行处理
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case Constants.Code.PERMISSION_SETING_CODE:
//                checkCameraPermission();
                break;
            default:

                break;
        }
    }

    @Override
    public void onBackPressed() {
        getDialog().alert("温馨提示", "您确定要退出app吗？", "确定", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearPopBackStack();
                activity.finish();
            }
        }, "取消", null);
    }

    public DialogHelper getDialog() {
        if (dialogHelper == null) {
            dialogHelper = new DialogHelper(MainActivity.this);
        }

        return dialogHelper;
    }


}
