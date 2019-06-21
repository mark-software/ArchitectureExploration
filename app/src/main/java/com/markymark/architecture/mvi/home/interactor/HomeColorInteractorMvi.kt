package com.markymark.architecture.mvi.home.interactor

import com.markymark.architecture.mvi.home.state.IHomeViewState
import com.markymark.architecture.mapper.HomeColorMapper
import com.markymark.architecture.rest.MyRestService
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers

class HomeColorInteractorMvi {

    fun getColors(): Observable<IHomeViewState> {
        return MyRestService.service.getColors(20)
            .observeOn(AndroidSchedulers.mainThread())
            .map { resources ->
                if(resources.data != null) {
                    IHomeViewState.Success(HomeColorMapper().mapModel(resources.data))
                }
                else {
                    IHomeViewState.Error(Throwable("Bad response!"))
                }
            }
            .startWith(IHomeViewState.Loading())
            .onErrorReturn {
                IHomeViewState.Error(it)
            }
    }
}