package com.markymark.architecture.mvc.base

import androidx.annotation.CallSuper

abstract class BaseController<V : BaseMvcView> {

    var view: V? = null
        private set


    @CallSuper
    internal fun attachView(view: V) {
        this.view = view

        viewAttached()
    }

    @CallSuper
    open fun viewAttached() {
        //override me
    }

    @CallSuper
    open fun viewDetatched() {
        view = null

        //override me
    }
}