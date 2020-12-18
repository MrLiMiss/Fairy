package com.beebank.newbee.bases.kotlin

import android.Manifest
import android.app.Activity
import android.os.Build
import android.os.Bundle
import com.bee.kotlin.mvp.IBaseView
import com.bee.kotlin.mvp.ITopPresenter
import com.bee.kotlin.mvp.IView
import com.bee.kotlin.util.ToastUtils
import com.beebank.newbee.utils.StatusBarUtil
import com.beebank.newbee.widgets.CommonDialog
import pub.devrel.easypermissions.EasyPermissions

/**
 * @FileName: BaseMvpActivity.java
 * @author: Leo
 * @date: 06-15:48
 * @version V1.0 <描述当前版本功能>
 * @desc
 */
abstract class BaseMvpAct<V : IBaseView, P : ITopPresenter> : BaseAct(), IView<P> {

    var dialog: CommonDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inited()
    }

    override fun getCtx() = this


    override fun finish(resultCode: Int) {
        finish()
    }


    override fun dismissLoading() {
        progressDialog?.dismiss()
    }

    override fun showToast(message: String) {
        message.let {
            ToastUtils().showToast(message)
        }
    }

    override fun showToast(srtResId: Int) {
        resources.getString(srtResId).let {
            ToastUtils().showToast(resources.getString(srtResId))
        }
    }

    override fun showLoading(msg: String) {
        progressDialog?.show()
    }

    override fun showLoading(srtResId: Int) {
        progressDialog?.show()
    }

    override fun showLoading() {
        progressDialog?.show()
    }


    /**
     * 申请权限
     */
    open fun requestPermissions() {
        EasyPermissions.requestPermissions(
                (mContext as Activity),
                "申请权限",
                0,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA,
                Manifest.permission.BLUETOOTH,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_WIFI_STATE,
                Manifest.permission.CHANGE_WIFI_STATE
        )
    }

    /**
     * 是否有权限
     *
     * @return
     */
    open fun havePermissions(): Boolean {
        val perms = arrayOf(
                Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA,
                Manifest.permission.BLUETOOTH,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_WIFI_STATE,
                Manifest.permission.CHANGE_WIFI_STATE
        )
        return EasyPermissions.hasPermissions(this, *perms)
    }

    override fun onBackPressed() {
        if (fragmentManager.backStackEntryCount == 1) {
            finish()
        } else {
            super.onBackPressed()
        }
    }

    override fun showErrorReqDiaolog(code: Int, message: String) {
        if (dialog == null) {
            dialog = CommonDialog.Builder(mContext).setMessage(message).setSingleButton("确认", null).build()
        }
        dialog?.showNormal(mContext as Activity?)

    }


}