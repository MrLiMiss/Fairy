package com.tengfei.fairy.designPattern;

import android.view.View;

import com.tengfei.fairy.R;
import com.tengfei.fairy.base.BaseActivity;
import com.tengfei.fairy.designPattern.chanResponsibily.handle.LoginOftenHandler;
import com.tengfei.fairy.designPattern.chanResponsibily.handle.NineElementHandler;
import com.tengfei.fairy.designPattern.chanResponsibily.handle.OutOfDataHandler;
import com.tengfei.fairy.utils.IntentUtils;

import butterknife.OnClick;

/**
 * @ Description :设计模式相关
 * @ Author 李腾飞
 * @ Time 2022/5/6   10:37 PM
 * @ Version :
 */
public class PatternActivity extends BaseActivity {
    @Override
    protected int getContentLayout() {
        return R.layout.activity_pattern;
    }

    @OnClick({R.id.tv_chain})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_chain:
                IntentUtils.toChainResponsibilyActivity(activity);
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
}
