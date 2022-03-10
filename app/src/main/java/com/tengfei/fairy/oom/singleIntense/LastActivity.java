package com.tengfei.fairy.oom.singleIntense;

import com.tengfei.fairy.R;
import com.tengfei.fairy.base.BaseActivity;

/**
 * @ Description :
 * @ Author 李腾飞
 * @ Time 2022/3/10   7:47 PM
 * @ Version :
 */
public class LastActivity extends BaseActivity {
    @Override
    protected int getContentLayout() {
        return R.layout.activity_last;
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
    public void onBackPressed() {
        finish();
    }
}
