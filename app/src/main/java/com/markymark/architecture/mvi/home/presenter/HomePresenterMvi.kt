package com.markymark.architecture.mvi.home.presenter

import com.hannesdorfmann.mosby3.mvi.MviBasePresenter
import com.markymark.architecture.mvi.home.interactor.HomeColorInteractorMvi
import com.markymark.architecture.mvi.home.state.IHomeViewState
import com.markymark.architecture.mvi.home.view.IHomeView
import io.reactivex.android.schedulers.AndroidSchedulers

class HomePresenterMvi : MviBasePresenter<IHomeView, IHomeViewState>() {

    private val interactor = HomeColorInteractorMvi()


    override fun bindIntents() {
        val homeObservable = intent(IHomeView::screenLoadedIntent)
            .switchMap {
                interactor.getColors()
            }
            .observeOn(AndroidSchedulers.mainThread())

        subscribeViewState(homeObservable, IHomeView::render)
    }
}