package com.bee.kotlin.util

import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.bee.kotlin.CommonInit


class ToastUtils {

    var mToast:Toast? = null
    var mHandler = Handler(Looper.getMainLooper())

    fun showToast(msg:String?){
        mHandler.post(Runnable {
            kotlin.run {
                mToast?.cancel()
                mToast = Toast.makeText(CommonInit.mContext,msg,Toast.LENGTH_SHORT)
                mToast?.show()
            }
        })
    }

//    fun showToast(msg: String?) {
//        mHandler.post(Runnable {
//            if (mToast != null) {
//                mToast!!.cancel()
//            }
//            mToast = Toast.makeText(CommonPack.getContext(), msg, Toast.LENGTH_SHORT)
//            mToast.show()
//        })
//    }
}