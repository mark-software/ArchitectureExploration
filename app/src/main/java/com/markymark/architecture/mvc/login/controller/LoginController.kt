package com.markymark.architecture.mvc.login.controller

import android.text.TextUtils
import com.markymark.architecture.mvc.base.BaseController
import com.markymark.architecture.mvc.base.BaseMvcView
import com.markymark.architecture.observer.BaseObserver
import com.markymark.architecture.rest.MyRestService
import com.markymark.architecture.rest.backendModel.request.LoginRequest
import com.markymark.architecture.rest.backendModel.response.LoginResponse
import io.reactivex.android.schedulers.AndroidSchedulers

class LoginController : BaseController<BaseMvcView>() {

    var modelResponse: LoginResponse? = null
        private set

    fun emailPasswordValid(email: CharSequence, password: CharSequence): Boolean {
        //Just using simple logic for now
        return !TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)
    }

    fun login(email: String, password: String) {
        MyRestService.service.login(LoginRequest(email, password))
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : BaseObserver<LoginResponse>() {
                override fun onNext(result: LoginResponse) {
                    modelResponse = result
                    view?.modelUpdated()
                }

                override fun onError(e: Throwable) {
                    super.onError(e)
                    modelResponse = null
                    view?.modelUpdated()
                }
            })
    }
}