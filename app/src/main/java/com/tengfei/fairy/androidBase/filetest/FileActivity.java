package com.tengfei.fairy.androidBase.filetest;

import com.tengfei.fairy.R;
import com.tengfei.fairy.mvp.BaseMvpActivity;
import com.tengfei.fairy.mvp.BasePresenter;

/**
 * @ Description :android 文件相关操作
 * @ Author 李腾飞
 * @ Time 12/27/21   2:27 PM
 * @ Version :
 */
public class FileActivity extends BaseMvpActivity<BasePresenter> {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_file;
    }

    @Override
    protected BasePresenter loadPresenter() {
        return null;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }
}
