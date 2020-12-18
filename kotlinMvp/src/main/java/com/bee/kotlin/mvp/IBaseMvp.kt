package com.bee.kotlin.mvp

import android.app.Activity
import android.content.Context
import androidx.annotation.StringRes
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import androidx.recyclerview.widget.RecyclerView
import com.bee.kotlin.view.MultipleStatusView
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import org.jetbrains.annotations.NotNull

interface IBaseView : LifecycleOwner {
    fun getCtx(): Context?
    fun inited()
    fun finish(resultCode: Int = Activity.RESULT_CANCELED)
    fun showLoading()
    fun showLoading(@NotNull msg: String)
    fun showLoading(@StringRes srtResId: Int)
    fun dismissLoading()
    fun showToast(@StringRes srtResId: Int)
    fun showToast(@NotNull message: String)
    fun showNoNetCon()
    fun showErrorCon()
    fun showErrorReqDiaolog(@NotNull code: Int, @NotNull message: String)

}

interface ITopPresenter : LifecycleObserver {
    fun attachView(view: IBaseView)

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun detachView()
}


interface ITopModel {
    fun onDetach()
}


interface IView<P : ITopPresenter> : IBaseView {
    var mPresenter: P
    override fun inited() {
        mPresenter.attachView(this)
    }
}

interface IPresenter<V : IBaseView, M : IModel> : ITopPresenter {
    var mView: V?
    var mModel: M?
    fun getContext() = mView?.getCtx()

    @Suppress("UNCHECKED_CAST")
    override fun attachView(view: IBaseView) {
        mView = view as V
        mView?.lifecycle?.addObserver(this)
    }

    override fun detachView() {
        mModel?.onDetach()
        mModel = null
        mView = null
    }

    //判断是否初始化View
    private val isViewAttached: Boolean
        get() = mView != null

    fun checkViewAttached() {
        if (!isViewAttached) throw MvpViewNotAttachedException()
    }

    private class MvpViewNotAttachedException internal constructor() : RuntimeException("Please call IPresenter.attachView(IBaseView) before" + " requesting data to the IPresenter")

}

interface IModel : ITopModel {
    val mDisposablePool: CompositeDisposable

    fun addDisposable(disposable: Disposable) {
        mDisposablePool.add(disposable)
    }

    override fun onDetach() {
        if (!mDisposablePool.isDisposed) {
            mDisposablePool.clear()
        }
    }
}

interface IListView<P : ITopPresenter> : IView<P> {
    val mRecyclerView: RecyclerView?
    val mMultipleView: MultipleStatusView?
    val mRefreshLayout: SmartRefreshLayout
    fun loadMoreFail(isRefresh: Boolean)
}