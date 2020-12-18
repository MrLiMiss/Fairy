package com.bee.kotlin.base

import com.bee.kotlin.mvp.IBaseView

open class BasePresenter<V: IBaseView>{
    var mView :V? = null
}