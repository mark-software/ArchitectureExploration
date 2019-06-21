package com.markymark.architecture.mvc.home.controller

import com.markymark.architecture.mvc.base.BaseController
import com.markymark.architecture.mvc.base.BaseMvcView
import com.markymark.architecture.observer.BaseObserver
import com.markymark.architecture.rest.MyRestService
import com.markymark.architecture.rest.backendModel.response.ResourceListResponse
import io.reactivex.android.schedulers.AndroidSchedulers

class HomeController : BaseController<BaseMvcView>() {

    var modelResponse: ResourceListResponse? = null
        private set


    override fun viewAttached() {
        super.viewAttached()

        getColorData()
    }

    private fun getColorData() {
        MyRestService.service.getColors(20)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : BaseObserver<ResourceListResponse>() {
                override fun onNext(result: ResourceListResponse) {
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
