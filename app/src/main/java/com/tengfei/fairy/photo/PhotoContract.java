package com.tengfei.fairy.photo;

import com.tengfei.fairy.mvp.BaseModel;
import com.tengfei.fairy.mvp.IModel;
import com.tengfei.fairy.mvp.IView;

/**
 * @ Description :图片存储，显示 contract
 * @ Author 李腾飞
 * @ Time 2020-11-23   11:19
 * @ Version :
 */
public interface PhotoContract {

    interface Model extends IModel {

        /**
         * 获取图片数据接口
         *
         * @param userName  用户名
         * @param phototype 图片类型  1-默认  4-手势密码
         * @param callback  回调
         */

        void getPhoto(String userName, int phototype, ResponseCallback<PhotoBean> callback);

    }

    interface View extends IView {
        void getPhotoSuccess(String message);
    }


    interface Presenter {
        void getPhoto(String userName, int phototype);
    }


    public interface ResponseCallback<T> {

        void success(T response);

        void fail(String s);

        void error(String error);

    }


}
