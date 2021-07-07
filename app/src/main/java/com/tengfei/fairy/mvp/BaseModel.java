package com.tengfei.fairy.mvp;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.tengfei.fairy.api.ApiService;
import com.tengfei.fairy.config.Config;
import com.tengfei.fairy.config.Constants;
import com.tengfei.fairy.retrofit.BaseBean;
import com.tengfei.fairy.retrofit.RetrofitFactory;
import com.tengfei.fairy.utils.LogUtils;
import com.tengfei.fairy.utils.SPUtils;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/**
 * @ Description :MVP  数据请求model 基类
 * @ Author 李腾飞
 * @ Time 2020-11-16   16:31
 * @ Version :
 */
public class BaseModel<P extends BasePresenter> implements IModel {

    protected P mPresenter;
    public BaseModel(P presenter) {
        this.mPresenter = presenter;
    }

    //call管理列表list
    protected final List<Call> listCalls = new ArrayList<>();
    protected Retrofit retrofit = RetrofitFactory.getRetrofit();
    protected ApiService apiService = retrofit.create(ApiService.class);
    protected CompositeDisposable compositeDisposable = new CompositeDisposable();

    public <T> void requeData(Call<BaseBean<T>> call, final ResponseCallBack<T> responseCallBack) {
        requeData(call, responseCallBack, false);
    }

    /**
     * @param call
     * @param responseCallBack
     * @param retry            true：注意重写retry()方法，可再次调用请求(这样重新生成请求体)
     *                         false：复制上一次请求体
     *                         原因：有的请求 用动态码加密，动态码过期后，再次请求，需要重新生成请求体
     * @param <T>
     */
    public <T> void requeData(Call<BaseBean<T>> call, final ResponseCallBack<T> responseCallBack, boolean retry) {
        synchronized (listCalls) {
            listCalls.add(call);
        }
        call.enqueue(new Callback<BaseBean<T>>() {
            @Override
            public void onResponse(Call<BaseBean<T>> call, Response<BaseBean<T>> response) {
                //从列表中移除
                call.cancel();
                listCalls.remove(call);
                BaseBean<T> body = response.body();
                if (body == null) {
                    responseCallBack.onFailed(new Throwable("网络连接异常"));
                    responseCallBack.onFailed("", new Throwable("网络连接异常"));
                    return;
                }
                if (Config.Code.SUCCESS.equals(body.code)) {
//                    if (!BuildConfig.API_HOST.equals("https://loan.hrbb.com.cn:9494/freeloan_api/")) {
//                        //方便测试查看
//                        Gson gson = new Gson();
//                        Constants.JSONData.responseData.add("接口地址：" + call.request().url() + "    " + gson.toJson(body));
//                    }
                    responseCallBack.onSuccess(body.data);
                } else if (Config.Code.DYNAMICKEY_TIME_OUT.equals(body.code)) {//验证码过期请求
//                    processDynamicKeyUnable(new OnCallBack() {
//
//                        @Override
//                        public void onSuccess() {
//                            //重新请求接口
//                            if (retry) {
//                                responseCallBack.retry();
//                            } else {
//                                requeData(call.clone(), responseCallBack);
//                            }
//                        }
//
//                        @Override
//                        public void onError(String errorMsg) {
//                            LogUtils.e(BaseModel.class,"onError = : "+errorMsg);
//                            responseCallBack.onFailed(new Throwable(errorMsg));
//                        }
//                    });
                } else if (Config.Code.LOGIN_AGIN.equals(body.code)) {
                    //需要重新登录
                    responseCallBack.onFailed(new Throwable(body.code + "," + body.msg));
                    mPresenter.loginAgin();
                } else {
                    String msg = TextUtils.isEmpty(body.msg) ? "未知错误" : body.msg;
                    responseCallBack.onFailed(new Throwable(msg + body.code));
                    responseCallBack.onFailed(body.code, new Throwable(msg));
                }
            }

            @Override
            public void onFailure(Call<BaseBean<T>> call, Throwable t) {
                //从列表中移除
                listCalls.remove(call);
                if (t instanceof ConnectException) {
                    responseCallBack.onFailed(new Throwable("网络连接异常"));
                    responseCallBack.onFailed("", new Throwable("网络连接异常"));
                } else {
                    responseCallBack.onFailed(t);
                    responseCallBack.onFailed("", t);
                }
            }
        });
    }

    /**
     * 清除所有请求
     */
    @Override
    public void clearAllCall() {
        synchronized (listCalls) {
            Iterator<Call> iterator = listCalls.iterator();
            while (iterator.hasNext()) {
                Call call = iterator.next();
                if (null == call || call.isCanceled()) {
                    continue;
                }
                LogUtils.e(BaseModel.class, "call is still living");
                call.cancel();
                iterator.remove();
            }
        }
        dispose();
    }

    //清空请求
    public void dispose() {
        if (null != compositeDisposable) {
            compositeDisposable.clear();
        }
    }

    public abstract class ResponseCallBack<T> implements CallBack<T> {
        public void onFailed(String code, Throwable t) {

        }

        public void onSuccess(String code,T t){

        }

        public void retry() {

        }

    }

    public interface CallBack<T> {
        void onSuccess(T t);

        void onFailed(Throwable t);
    }


//    /**
//     * 处理动态码过期请求
//     */
//    public void processDynamicKeyUnable(OnCallBack callBack) {
//        Disposable disposable = apiService.getDynamicKey()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<BaseBean<DynamicKeyBean>>() {
//                    @Override
//                    public void accept(BaseBean<DynamicKeyBean> dynamicKeyBeanBaseBean) throws Exception {
//                        if (dynamicKeyBeanBaseBean == null) {
//                            //error
//                            callBack.onError("动态码请求失败");
//                        } else if (Config.Code.LOGIN_AGIN.equals(dynamicKeyBeanBaseBean.code)) {
//                            //需要重新登录
//                            callBack.onError(dynamicKeyBeanBaseBean.msg);
//                            mPresenter.loginAgin();
//                        } else if (!Config.Code.SUCCESS.equals(dynamicKeyBeanBaseBean.code) || dynamicKeyBeanBaseBean.data == null ||
//                                TextUtils.isEmpty(dynamicKeyBeanBaseBean.data.dynamicKey)) {
//                            callBack.onError(TextUtils.isEmpty(dynamicKeyBeanBaseBean.msg) ? "动态码请求失败" : dynamicKeyBeanBaseBean.msg);
//                        } else {
//                            //success
//                            //存储动态码
//                            SPUtils.put(Constants.SPKey.AES_DYNAMICKEY, dynamicKeyBeanBaseBean.data.dynamicKey);
//                            callBack.onSuccess();
//                        }
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Exception {
//                        callBack.onError(throwable.getMessage());
//                    }
//                });
//        compositeDisposable.add(disposable);
//    }


    public interface OnCallBack {
        void onSuccess();

        void onError(String errorMsg);
    }

}

