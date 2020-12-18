package com.beebank.newbee.bases.kotlin

import android.app.Activity
import android.os.Bundle
import com.bee.kotlin.mvp.IBaseView
import com.bee.kotlin.mvp.ITopPresenter
import com.bee.kotlin.mvp.IView
import com.bee.kotlin.util.ToastUtils
import com.beebank.newbee.bases.BaseFragmentActivity
import com.beebank.newbee.widgets.CommonDialog

abstract class BaseMvpFrag<V : IBaseView, P : ITopPresenter> : BaseFrag(), IView<P> {
    override fun getCtx() = context

    var dialog: CommonDialog? = null


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        inited()
    }

    override fun finish(resultCode: Int) {
    }

    override fun showToast(message: String) {
        message.let {
            ToastUtils().showToast(message)
        }
    }

    override fun showToast(srtResId: Int) {
        showToast(resources.getString(srtResId))
    }

    override fun showLoading(msg: String) {

    }

    override fun showLoading(srtResId: Int) {

    }

    override fun showErrorReqDiaolog(code: Int, message: String) {
        if (dialog == null) {
            dialog = CommonDialog.Builder(mContext).setMessage(message).setSingleButton("确认", null).build()
        }
        dialog?.showNormal(mContext as Activity?)
    }


    override fun showNoNetCon() {

    }


    override fun showErrorCon() {

    }

}