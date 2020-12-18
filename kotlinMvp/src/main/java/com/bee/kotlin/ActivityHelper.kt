package com.bee.kotlin

import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import java.lang.Exception
import java.util.*

class ActivityHelper private constructor(){
    var mActStack:Stack<Activity>? = null

    companion object{
        val instance:ActivityHelper by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED){
            ActivityHelper()
        }
    }

    /**
     * 添加Act
     */
    fun addAct(activity: Activity){
        if(mActStack == null)
            mActStack = Stack()
        mActStack!!.add(activity)
    }

    /**
     * 获取当前Act
     */
    fun getCurrentAct():Activity{
        return mActStack!!.lastElement()
    }

    /**
     * 结束传入的Act
     */
    fun finishAct(activity: Activity?){
        var activity = activity
        if(activity != null){
            mActStack!!.remove(activity)
            activity.finish()
            activity = null
        }
    }

    /**
     * 结束特定类名的Act
     */
    fun removeAct(cls:Class<*>){
        try {
            for (activity in mActStack!!){
                if(activity.javaClass == cls)
                    finishAct(activity)
            }
        }catch (e:Exception){

        }
    }

    /**
     * 结束所有Act
     */
    fun finishAllAct(){
        for (i in mActStack!!.indices){
            if(null != mActStack!![i]){
                mActStack!![i].finish()
            }
        }
    }

    /**
     * 推出整个APP
     */
    fun AppExit(mContext:Context){
        try {
            finishAllAct()
            val actMgr = mContext.getSystemService(Context.ACTIVITY_SERVICE)
             as ActivityManager
            actMgr.killBackgroundProcesses(mContext.packageName)
            System.exit(0)
        }catch (e:Exception){

        }
    }




}