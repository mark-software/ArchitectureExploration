package com.markymark.architecture.mvi.login.view

import com.hannesdorfmann.mosby3.mvp.MvpView
import com.markymark.architecture.mvi.login.model.Credentials
import com.markymark.architecture.mvi.login.state.ILoginViewState
import io.reactivex.Observable

interface ILoginView: MvpView {
    fun loginIntent(): Observable<Credentials>
    fun render(state: ILoginViewState)
}