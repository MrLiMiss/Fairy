package com.tengfei.fairy.jni.aesJni;

import android.util.Log;
import android.view.View;

import com.tengfei.fairy.R;
import com.tengfei.fairy.mvp.BaseMvpActivity;
import com.tengfei.fairy.mvp.BasePresenter;

import butterknife.OnClick;

/**
 * @ Description :JNI 测试
 * @ Author 李腾飞
 * @ Time 1/26/22   10:02 AM
 * @ Version :
 */
public class JniActivity extends BaseMvpActivity<BasePresenter> {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_jni;
    }

    @Override
    protected BasePresenter loadPresenter() {
        return null;
    }

    @Override
    protected void initView() {

    }

    @OnClick({R.id.btn_aes})
    public void click(View view){
        switch (view.getId()){
            case R.id.btn_aes:
                aesJni();
                break;
            default:
                break;
        }
    }

    private void aesJni() {

//        String text = "abc_-=.,123扫地阿姨发现你的代码有Bug";
//        String textEnc = FooTools.method01(text);
//        String textDec = FooTools.method02(textEnc);
//
//        Log.d("aes", "text: " + text);
//        Log.d("aes", "text 加码: " + textEnc);
//        Log.d("aes", "text 解密: " + textDec);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }
}
