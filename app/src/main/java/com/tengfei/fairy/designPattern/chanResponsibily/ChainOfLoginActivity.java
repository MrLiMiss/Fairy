package com.tengfei.fairy.designPattern.chanResponsibily;

import android.view.View;

import com.tengfei.fairy.R;
import com.tengfei.fairy.base.BaseActivity;
import com.tengfei.fairy.designPattern.chanResponsibily.handle.LoginOftenHandler;
import com.tengfei.fairy.designPattern.chanResponsibily.handle.NineElementHandler;
import com.tengfei.fairy.designPattern.chanResponsibily.handle.OutOfDataHandler;
import com.tengfei.fairy.utils.Logs;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.OnClick;

/**
 * @ Description :责任链模式：为请求创建了一个接收者对象的链。这种模式给予请求的类型，对请求的发送者和接收者进行解耦。这种类型的设计模式属于行为型模式。
 *                登录成功后处理逻辑伪代码
 * @ Author 李腾飞
 * @ Time 2022/5/6   3:56 PM
 * @ Version :
 */
public class ChainOfLoginActivity extends BaseActivity {
    public static String TAG=ChainOfLoginActivity.class.getSimpleName();
    private UserInfoVo userInfoVo;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_chain_login;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onReceiveOften(UserInfoVo userInfoVo){
        Logs.d(TAG,"ltf-校验处理完毕");
    }

    @OnClick({R.id.tv_detail_login})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_detail_login:
                LoginOftenHandler loginOftenHandler=new LoginOftenHandler();
                NineElementHandler nineElementHandler=new NineElementHandler();
                OutOfDataHandler outOfDataHandler=new OutOfDataHandler();

                loginOftenHandler.setNext(nineElementHandler);
                nineElementHandler.setNext(outOfDataHandler);
                loginOftenHandler.handleRequest(activity, userInfoVo);
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
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void initData() {
        userInfoVo = new UserInfoVo("111","020","often","fullNineElement",true);
        userInfoVo.setIsOften("notOften");//非常用设备
        userInfoVo.setNineElement("incomplete");//就要素不完整

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
