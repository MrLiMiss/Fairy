package com.beebank.newbee.bases.kotlin

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import butterknife.ButterKnife
import butterknife.Unbinder
import com.beebank.newbee.R
import com.beebank.newbee.SDMoneyApplication
import com.beebank.newbee.common.ServiceManager
import com.beebank.newbee.common.utils.LogUtil
import com.beebank.newbee.widgets.CommonDialog
import com.beebank.newbee.widgets.CommonLoadingDialog
import com.gyf.immersionbar.ktx.immersionBar

abstract class BaseFrag : Fragment() {

    private var isViewPrepare = false
    private var hasLoadData = false
    open var mContext: Context? = null
    open var loadingDialog: CommonLoadingDialog? = null
    private var unbinder: Unbinder? = null
    protected var mServiceManager: ServiceManager? = null

    protected abstract fun lazyLoad()

    @LayoutRes
    protected abstract fun getContentView(): Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val layout = getContentView()
        val rootView = inflater.inflate(layout, container, false)
        this.mContext = context
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isViewPrepare = true
        initView(view)
        initClick()
        loadingDialog = CommonLoadingDialog.Builder(activity).build()
        savedStanceState(savedInstanceState)
        initData()
        lazyLoadDataIfPrepared()
    }

    protected abstract fun initView(view: View)
    protected abstract fun initClick()
    open fun savedStanceState(savedInstanceState: Bundle?) {}
    protected abstract fun initData()


    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            lazyLoadDataIfPrepared()
        }
    }

    private fun lazyLoadDataIfPrepared() {
        if (userVisibleHint && isViewPrepare && !hasLoadData) {
            lazyLoad()
        }
    }

    init {
        mServiceManager = SDMoneyApplication.getInstance().serviceManager
    }



    fun bindViews(`object`: Any?, view: View?) {
        unbinder = ButterKnife.bind(`object`!!, view!!)
    }

    fun unbindViews() {
        if (unbinder != null) {
            try {
                unbinder?.unbind()
            } catch (e: Exception) {
                LogUtil.e(e.message.toString())
            }
        }
    }

    fun showLoading() {
        if (loadingDialog != null) {
            if (loadingDialog!!.isShowing) {
                loadingDialog?.dismiss()
            }
        } else {
            loadingDialog = CommonLoadingDialog.Builder(context).build()
        }
        loadingDialog?.show()
    }

    fun dismissLoading() {
        if (loadingDialog != null && loadingDialog!!.isShowing) {
            loadingDialog?.dismiss()
        }
    }


    fun onBackPressed(): Boolean {
        return false
    }


    protected open fun showTipsDialog(msg: String?) {
        val commonDialog = CommonDialog.Builder(activity)
        commonDialog.addBankLimit()
        commonDialog.setMessage(msg)
        commonDialog.setSingleButton(getString(R.string.sdf_common_ok)) { }.build().show()
    }
}