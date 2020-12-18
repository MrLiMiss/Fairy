package com.beebank.newbee.bases.kotlin

import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import cn.addapp.pickers.listeners.OnItemPickListener
import com.bee.kotlin.util.ActivityUtils.pushActivity
import com.bee.kotlin.util.ActivityUtils.removeActivity
import com.beebank.newbee.R
import com.beebank.newbee.SDMoneyApplication
import com.beebank.newbee.account.activity.LoginActivity
import com.beebank.newbee.cache.UserInfoCache
import com.beebank.newbee.common.Constants
import com.beebank.newbee.common.token.TokenEvent
import com.beebank.newbee.utils.SharedPrefUtil
import com.beebank.newbee.utils.StatusBarUtil
import com.beebank.newbee.utils.ViewUtils
import com.beebank.newbee.widgets.CommonDialog
import com.beebank.newbee.widgets.CommonLoadingDialog
import com.beebank.newbee.widgets.ItemSelectView
import com.gyf.immersionbar.ktx.immersionBar
import com.igexin.sdk.PushManager
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import kotlin.properties.Delegates

/**
 * @author: Leo
 * @version V1.0 Activity的基类
 * @desc
 */
abstract class BaseAct : AppCompatActivity() {
    open var mContext: Context by Delegates.notNull()//非空属性:Delegates.notNull()
    open var progressDialog: CommonLoadingDialog? = null
    open fun initData() {}
    open fun onSetContentViewNext(savedInstanceState: Bundle?) {}

    @LayoutRes
    abstract fun getContentView(): Int
    abstract fun initView()
    abstract fun initListener()
    override fun onCreate(savedInstanceState: Bundle?) {
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        super.onCreate(savedInstanceState)
        mContext = this
        setContentView(getContentView())
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this)
        }

        StatusBarUtil.setStatusBar(this, 0)
        if (StatusBarUtil.isMIUI() && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //设置statusbar字体高亮
            StatusBarUtil.setXiaomiStatusBar(window, true)
        }

        initView()
        progressDialog = CommonLoadingDialog.Builder(this).build()
        onSetContentViewNext(savedInstanceState)
        pushActivity(this)
        initListener()
        initData()
    }



    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        if (ev.action == MotionEvent.ACTION_UP) {
            val v = currentFocus
            //如果不是落在EditText区域，则需要关闭输入法
            if (HideKeyboard(v, ev)) {
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(v!!.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
            }
        }
        return super.dispatchTouchEvent(ev)
    }

    // 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘
    private fun HideKeyboard(view: View?, event: MotionEvent): Boolean {
        if (view != null && view is EditText) {

            val location = intArrayOf(0, 0)
            view.getLocationInWindow(location)

            //获取现在拥有焦点的控件view的位置，即EditText
            val left = location[0]
            val top = location[1]
            val bottom = top + view.height
            val right = left + view.width
            //判断我们手指点击的区域是否落在EditText上面，如果不是，则返回true，否则返回false
            val isInEt = (event.x > left && event.x < right && event.y > top
                    && event.y < bottom)
            return !isInEt
        }
        return false
    }


    override fun onDestroy() {
        removeActivity(this)
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }


    fun showSelectDialog(view: ItemSelectView, array: MutableList<String>?) {
        ViewUtils.showSelectDialog(this, array) { i, s -> view.text = s }
    }

    open fun showSelectDialog(view: ItemSelectView?, array: List<String?>?, listener: OnItemPickListener<Any?>?) {
        ViewUtils.showSelectDialog(this, array) { i, s -> listener?.onItemPicked(i, s) }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onTokenEvent(event: TokenEvent) {
        try {
            UserInfoCache.getCache().loginOut()
            SDMoneyApplication.getInstance().getServiceManager().getTokenService().refreshAccessToken("")
            SharedPrefUtil.clear(null)
            PushManager.getInstance().turnOffPush(applicationContext)
            val nm = parent.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            nm.cancelAll()
        } catch (e: Exception) {
        }
        if (event.code == Constants.error.ERROR_TOKEN_UNAVAILABLE) {
            showOtherLogin(event.msg)
        } else {
            val intent = Intent(this, LoginActivity::class.java)
            startActivityForResult(intent, 0)
        }
    }

    fun showOtherLogin(msg: String) {
        val commonDialog = CommonDialog.Builder(this)
        commonDialog.addBankLimit()
        commonDialog.setMessage(msg)
        commonDialog.setSingleButton(getString(R.string.sdf_common_ok)) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivityForResult(intent, 0)
        }.build().show()
    }

}