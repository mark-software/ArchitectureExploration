package com.markymark.architecture.mvp.login.view

import com.hannesdorfmann.mosby3.mvp.MvpView

interface ILoginView: MvpView {
    fun showProgress(show: Boolean)
    fun showToast(text: String)
    fun showToast(intRes: Int)
    fun launchHomeScreenActivity()
    fun hideKeyboard()
}