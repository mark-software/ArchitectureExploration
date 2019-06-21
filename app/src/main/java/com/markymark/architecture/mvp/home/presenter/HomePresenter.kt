package com.markymark.architecture.mvp.home.presenter

import com.hannesdorfmann.mosby3.mvp.MvpNullObjectBasePresenter
import com.markymark.architecture.mvp.home.view.IHomeView
import com.markymark.architecture.mapper.HomeColorMapper
import com.markymark.architecture.observer.BaseObserver
import com.markymark.architecture.rest.MyRestService
import com.markymark.architecture.rest.backendModel.response.ResourceListResponse
import io.reactivex.android.schedulers.AndroidSchedulers

class HomePresenter: MvpNullObjectBasePresenter<IHomeView>() {

    fun updateColorData() {
        MyRestService.service.getColors(20)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : BaseObserver<ResourceListResponse>() {
                override fun onNext(result: ResourceListResponse) {
                    view.setAdapterItems(HomeColorMapper().mapModel(result.data).orEmpty())
                }

                override fun onError(e: Throwable) {
                    super.onError(e)
                    view.showToast(e.localizedMessage)
                }
            })
    }
}