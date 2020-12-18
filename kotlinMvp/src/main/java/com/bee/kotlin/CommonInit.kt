package com.bee.kotlin

import android.content.Context
import com.bee.kotlin.helper.SharedPrefHelper

class CommonInit {

    companion object{

        var mContext:Context? = null
        fun init(context: Context){
            Companion.mContext = context
            SharedPrefHelper().mContext = context
        }
    }

}