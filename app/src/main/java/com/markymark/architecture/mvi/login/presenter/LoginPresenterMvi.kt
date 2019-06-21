package com.markymark.architecture.mvi.login.presenter

import com.hannesdorfmann.mosby3.mvi.MviBasePresenter
import com.markymark.architecture.mvi.login.interactor.LoginInteractor
import com.markymark.architecture.mvi.login.state.ILoginViewState
import com.markymark.architecture.mvi.login.view.ILoginView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers

class LoginPresenterMvi : MviBasePresenter<ILoginView, ILoginViewState>() {

    private val interactor = LoginInteractor()


    override fun bindIntents() {
        val loginObservable: Observable<ILoginViewState> =
            intent(ILoginView::loginIntent)
                .switchMap {
                    interactor.login(it.email, it.password)
                }
                .observeOn(AndroidSchedulers.mainThread())

        subscribeViewState(loginObservable, ILoginView::render)
    }
}