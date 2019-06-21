package com.markymark.architecture.mvvm.home.interactor

import com.markymark.architecture.mapper.HomeColorMapper
import com.markymark.architecture.mvvm.home.model.ColorPresentationModel
import com.markymark.architecture.mvvm.home.model.InteractorResponse
import com.markymark.architecture.rest.MyRestService
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers

class HomeColorInteractor {

    fun getColors(): Observable<InteractorResponse<List<ColorPresentationModel>?>> {

        return MyRestService.service.getColors(20)
            .observeOn(AndroidSchedulers.mainThread())
            .map { resources ->
                InteractorResponse(
                    HomeColorMapper().mapModel(
                        resources.data
                    ), null, null
                )
            }
            .onErrorReturn {
                InteractorResponse(null, it.localizedMessage, it)
            }
    }
}