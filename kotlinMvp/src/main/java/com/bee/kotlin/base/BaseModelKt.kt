package com.bee.kotlin.base

import io.reactivex.disposables.CompositeDisposable

open class BaseModelKt {
    val mDisposablePool: CompositeDisposable by lazy { CompositeDisposable() }


}