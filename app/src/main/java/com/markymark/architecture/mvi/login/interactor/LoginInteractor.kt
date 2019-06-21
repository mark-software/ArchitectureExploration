package com.markymark.architecture.mvi.login.interactor

import com.markymark.architecture.mvi.login.state.ILoginViewState
import com.markymark.architecture.rest.MyRestService
import com.markymark.architecture.rest.backendModel.request.LoginRequest
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers

class LoginInteractor {

    fun login(email: String, password: String): Observable<ILoginViewState> {

        if (email.isEmpty() || password.isEmpty()) {
            return Observable.just(ILoginViewState.NotStarted())
        }

        return MyRestService.service.login(LoginRequest(email, password))
            .observeOn(AndroidSchedulers.mainThread())
            .map { response ->
                if (response.token == null) {
                    ILoginViewState.Error(Throwable("Bad response!"))
                } else {
                    ILoginViewState.Success()
                }
            }
            .startWith(ILoginViewState.Loading())
            .onErrorReturn { ILoginViewState.Error(it) }

            //Alternative way to say .endWith()
            .concatWith(Observable.just(ILoginViewState.NotStarted()))
    }
}