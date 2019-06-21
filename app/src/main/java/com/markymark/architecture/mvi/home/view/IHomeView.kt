package com.markymark.architecture.mvi.home.view

import com.hannesdorfmann.mosby3.mvp.MvpView
import com.markymark.architecture.mvi.home.state.IHomeViewState
import io.reactivex.Observable

interface IHomeView: MvpView {
    fun screenLoadedIntent(): Observable<Boolean>
    fun render(state: IHomeViewState)
}