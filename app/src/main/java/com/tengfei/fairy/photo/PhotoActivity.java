package com.tengfei.fairy.photo;

import com.tengfei.fairy.R;
import com.tengfei.fairy.mvp.BaseMvpActivity;
import com.tengfei.fairy.utils.ToastUtils;


/**
 * @ Description :照片相关activity
 * @ Author 李腾飞
 * @ Time 2020-11-23   14:52
 * @ Version :
 */
public class PhotoActivity extends BaseMvpActivity<PhotoPresenter> implements PhotoContract.View {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_photo;
    }

    @Override
    protected PhotoPresenter loadPresenter() {
        return new PhotoPresenter();
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


    /**
     * 点击事件
     * @param userName 用户名
     * @param photoType 照片类型
     */
    public void getPhoto(String userName,int photoType){
           mPresenter.getPhoto(userName,photoType);
    }

    @Override
    public void getPhotoSuccess(String message) {

        ToastUtils.showToast("获取图片成功，可以进行下一步操作");

    }

}
