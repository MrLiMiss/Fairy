package com.tengfei.fairy.designPattern.chanResponsibily;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.tengfei.fairy.R;
import com.tengfei.fairy.base.BaseActivity;
import com.tengfei.fairy.utils.IntentUtils;
import com.tengfei.fairy.utils.Logs;

import org.greenrobot.eventbus.EventBus;

import butterknife.OnClick;

/**
 * @ Description :
 * @ Author 李腾飞
 * @ Time 2022/5/6   4:14 PM
 * @ Version :
 */
public class AfterLoginCheckActivity extends BaseActivity {
    public static String TAG = AfterLoginCheckActivity.class.getSimpleName();
    private UserInfoVo userInfoVo1;
    private boolean oftenChecked = false;//默认非常用设备

    @Override
    protected int getContentLayout() {
        return R.layout.activity_after_login;
    }


    @OnClick({R.id.tv_detail_often, R.id.tv_detail_nine_element, R.id.tv_detail_out_of_date})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_detail_often:
                userInfoVo1 = new UserInfoVo("111", "020", "often", "fullNineElement", true);
                oftenChecked = true;
//                Intent intent=new Intent(activity,ChainOfLoginActivity.class);
//                activity.startActivity(intent);
                activity.finish();
                break;
            case R.id.tv_detail_nine_element:
                UserInfoVo userInfoVo2 = new UserInfoVo("111", "020", "often", "NineElement", true);
                userInfoVo2.setNineElement("fullNineElement");
                EventBus.getDefault().post(userInfoVo2);
                Logs.d(TAG, "ltf-九要素校验完毕，发送EventBus");
                break;
            case R.id.tv_detail_out_of_date:
                UserInfoVo userInfoVo3 = new UserInfoVo("111", "020", "often", "NineElement", true);
                userInfoVo3.setOutOfDate(false);
                EventBus.getDefault().post(userInfoVo3);
                Logs.d(TAG, "ltf-身份证信息更新完毕，发送EventBus");
                break;
            default:
                break;
        }
    }

    @Override
    protected void initGui() {

    }

    @Override
    protected void initAction() {

    }

    @Override
    protected void initData() {

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (oftenChecked){
            Logs.d(TAG, "ltf-常用设备校验完毕，发送EventBus");
            EventBus.getDefault().post(userInfoVo1);
        }
    }
}
