package com.tengfei.fairy.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.View;
import android.widget.TableLayout;

import com.google.android.material.tabs.TabLayout;
import com.tengfei.fairy.R;
import com.tengfei.fairy.base.BaseActivity;
import com.tengfei.fairy.constant.Constants;
import com.tengfei.fairy.fragment.HonorFragment;
import com.tengfei.fairy.fragment.LifeFragemnt;
import com.tengfei.fairy.fragment.MainFragment;
import com.tengfei.fairy.fragment.MyFragment;
import com.tengfei.fairy.utils.DialogHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class MainActivity extends BaseActivity {

    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.tabLayout)
    TabLayout tablayout;

    private DialogHelper dialogHelper;

    private String [] tabs={"主页","荣耀","生活","我的"};
    private Fragment[] fragments={new MainFragment(),new HonorFragment(),new LifeFragemnt(),new MainFragment()};

    @Override
    protected int getContentLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initGui() {

    viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
        @NonNull
        @Override
        public Fragment getItem(int position) {
           return fragments[position];
        }

        @Override
        public int getCount() {
            return tabs.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabs[position];
        }


    });

        tablayout.setupWithViewPager(viewPager);
        tablayout.getTabAt(1).select();//设置第一个为选中

    }

    @Override
    protected void initAction() {

    }

    protected void initData() {


    }




//    /**
//     * 处理权限申请的回调。
//     */
//    @Override
//    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//        if (requestCode == Constants.Code.PERMISSION_CALL_PHONE) {
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {//允许权限
////                IntentUtils.startoHotLine(mActivity);
//                Log.e("onRequestPermissions","允许权限");
//            } else {//拒绝权限
//
//                if (!ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, permissions[0])) {
//                    //不再提示
//                    Log.e("onRequestPermissions","拒绝不再提示");
//
//                } else {//不再提示--deny
////                    ToastTools.showLong(this,"请允许权限");
//                }
//            }
//        }
//    }
//
//
//    @Override     //startActivityforresult(requestcode),跳转到其他界面返回结果（resultCode）进行处理
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        switch (requestCode) {
//            case Constants.Code.PERMISSION_SETING_CODE:
////                checkCameraPermission();
//                break;
//            default:
//
//                break;
//        }
//    }

    @Override
    public void onBackPressed(){
        getDialog().alert("温馨提示", "您确定要退出app吗？", "确定", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        },"取消", null);
    }

    public DialogHelper getDialog() {
        if (dialogHelper == null) {
            dialogHelper = new DialogHelper(MainActivity.this);
        }

        return dialogHelper;
    }




}
