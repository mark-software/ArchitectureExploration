package com.markymark.architecture.mvvm.login.viewModel

import android.text.TextUtils
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.markymark.architecture.mvvm.login.model.LoginStateModel
import com.markymark.architecture.observer.BaseObserver
import com.markymark.architecture.rest.MyRestService
import com.markymark.architecture.rest.backendModel.request.LoginRequest
import com.markymark.architecture.rest.backendModel.response.LoginResponse
import io.reactivex.android.schedulers.AndroidSchedulers

class LoginViewModel: ViewModel() {

    private val loading: MutableLiveData<Boolean> = MutableLiveData()
    private val messages: MutableLiveData<String> = MutableLiveData()
    private val loginState: MutableLiveData<LoginStateModel> = MutableLiveData()


    private fun emailPasswordValid(email: CharSequence, password: CharSequence): Boolean {
        //Just using simple logic for now
        return !TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)
    }

    fun login(email: String, password: String) {
        if(!emailPasswordValid(email, password)) {
            messages.value = "Email or password is empty!"
            return
        }

        loading.value = true

        MyRestService.service.login(LoginRequest(email, password))
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : BaseObserver<LoginResponse>() {
                override fun onNext(result: LoginResponse) {
                    loading.value = false
                    loginState.value = LoginStateModel(true, "Success!")
                }

                override fun onError(e: Throwable) {
                    super.onError(e)

                    loading.value = false
                    loginState.value = LoginStateModel(false, e.localizedMessage)
                }
            })
    }

    fun observeLoading() = loading
    fun observeMessages() = messages
    fun observeLoginState() = loginState

}
