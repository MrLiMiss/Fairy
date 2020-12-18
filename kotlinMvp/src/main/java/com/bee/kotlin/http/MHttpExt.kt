package com.bee.kotlin.http

import com.bee.kotlin.mvp.IBaseView
import com.bee.kotlin.mvp.IModel
import com.bee.kotlin.util.SchedulerUtils
import com.bee.kotlin.util.ToastUtils
import com.beebank.newbee.common.CommonConfig
import com.beebank.newbee.common.Constants
import com.beebank.newbee.common.ServiceManager
import com.beebank.newbee.common.http.HttpResponse
import com.google.gson.JsonParseException
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeUnit

fun <T> Observable<T>.mSubscribe(
        iBaseView: IBaseView? = null
        , iModel: IModel? = null
        , msg: String = ""
        , onSuccess: (T) -> Unit) {
    this.compose(SchedulerUtils.ioToMain())
            .subscribe(object : Observer<T> {
                override fun onComplete() {
                    iBaseView?.dismissLoading()
                }

                override fun onSubscribe(d: Disposable) {
                    iModel?.mDisposablePool?.add(d)
                    iBaseView?.showLoading(if (msg.isEmpty()) "请求中..." else msg)
                }

                override fun onNext(t: T) {
                    val bean = t as HttpResponse
                    if (bean.retcode == Constants.error.SUCCESS) {
                        onSuccess.invoke(t)
                    } else if (bean.retcode == Constants.error.ERROR_TOKEN_UNAVAILABLE || bean.retcode == Constants.error.ERROR_TOKEN_TIMEOUT) {
                        CommonConfig.getServiceManager().tokenService.disableToken(bean.retcode, msg)
                    } else {
                        t.let {
                            if (!t.msg.isNullOrEmpty()) {
                                t.msg.let {
                                    ToastUtils().showToast(it)
                                }
                            } else {
                                ToastUtils().showToast("请求失败")
                            }
                        }

                        iBaseView?.showErrorCon()
                    }
                }

                override fun onError(e: Throwable) {
                    iBaseView?.dismissLoading()
                    if (e is SocketTimeoutException || e is ConnectException || e is UnknownHostException) {
                        ToastUtils().showToast("连接失败,请检查网络状况!")
                        iBaseView?.showNoNetCon()
                    } else if (e is JsonParseException) {
                        ToastUtils().showToast("数据解析失败")
                        iBaseView?.showErrorCon()
                    } else {
                        ToastUtils().showToast("未知错误")
                        iBaseView?.showErrorCon()
                    }
                }
            })
}


/**
 * 统一处理方法,弹窗,内存泄漏,线程调度
 */
fun <T> Observable<T>.bindDialogAndDisposable(view: IBaseView? = null, iModel: IModel? = null, msg: String? = null): Observable<T> = this.async().bindDialog(view, msg).bindDisposable(iModel)

/**
 * 线程切换,回调回主线程
 */
fun <T> Observable<T>.async(withDelay: Long = 0): Observable<T> =
        this.subscribeOn(Schedulers.io())
                .delay(withDelay, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())

/**
 * 线程切换,回调回子线程
 */
fun <T> Observable<T>.asyncIo(withDelay: Long = 0): Observable<T> =
        this.subscribeOn(Schedulers.io())
                .delay(withDelay, TimeUnit.MILLISECONDS)
                .observeOn(Schedulers.io())

/**
 * 绑定弹窗
 */
fun <T> Observable<T>.bindDialog(view: IBaseView? = null, msg: String? = null): Observable<T> =
        this.doOnSubscribe {
            view?.showLoading(msg ?: "加载中...")
        }.doOnComplete {
            view?.dismissLoading()
        }

/**
 * 绑定内存泄漏
 */
fun <T> Observable<T>.bindDisposable(iModel: IModel? = null): Observable<T> =
        this.doOnSubscribe {
            iModel?.mDisposablePool?.add(it)
        }

/**
 * 处理结果
 */
fun <T> Observable<T>.onResult(subErr: ((Throwable) -> Unit)? = null, success: ((T) -> Unit)? = null) =
        this.subscribe({
            val bean = it as HttpResponse
            when (bean.retcode) {
                Constants.error.SUCCESS -> success?.invoke(it)
//            bean.code == HttpConfig.LOGIN_OUT -> {//重新登录
//            }
                else -> {
                    ToastUtils().showToast(if (it.msg?.isNotEmpty()!!) it.msg else "请求失败")
                }
            }
            success?.invoke(it)
        }, {
            ToastUtils().showToast("请求失败")
            subErr?.invoke(it)
        })
