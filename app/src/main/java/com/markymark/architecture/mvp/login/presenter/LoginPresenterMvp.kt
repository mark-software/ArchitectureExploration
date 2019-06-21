package com.markymark.architecture.mvp.login.presenter

import android.text.TextUtils
import com.hannesdorfmann.mosby3.mvp.MvpNullObjectBasePresenter
import com.markymark.architecture.R
import com.markymark.architecture.mvp.login.view.ILoginView
import com.markymark.architecture.observer.BaseObserver
import com.markymark.architecture.rest.MyRestService
import com.markymark.architecture.rest.backendModel.request.LoginRequest
import com.markymark.architecture.rest.backendModel.response.LoginResponse
import io.reactivex.android.schedulers.AndroidSchedulers

/**
 * Everything is controlled by the presenter here.
 * The view is very "dumb".
 */
class LoginPresenterMvp: MvpNullObjectBasePresenter<ILoginView>() {


    private fun emailPasswordValid(email: CharSequence, password: CharSequence): Boolean {
        //Just using simple logic for now
        return !TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)
    }

    fun login(email: String, password: String) {
        if(!emailPasswordValid(email, password)) {
            view.showToast(R.string.name_pw_empty)
            return
        }

        view.hideKeyboard()
        view.showProgress(true)

        MyRestService.service.login(LoginRequest(email, password))
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : BaseObserver<LoginResponse>() {
                override fun onNext(result: LoginResponse) {
                    view.showProgress(false)
                    view.launchHomeScreenActivity()
                }

                override fun onError(e: Throwable) {
                    super.onError(e)
                    view.showProgress(false)
                    view.showToast(e.localizedMessage)
                }
            })
    }
}