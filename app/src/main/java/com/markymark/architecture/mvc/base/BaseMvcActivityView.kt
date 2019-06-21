package com.markymark.architecture.mvc.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseMvcActivityView<V : BaseMvcView, C : BaseController<V>> : AppCompatActivity(), BaseMvcView {

    var controller: C? = null
        private set


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        controller = createController()
        controller?.attachView(getView())
    }

    override fun onDestroy() {
        controller?.viewDetatched()

        super.onDestroy()
    }

    abstract fun createController(): C

    override fun getView() : V = this as V
}