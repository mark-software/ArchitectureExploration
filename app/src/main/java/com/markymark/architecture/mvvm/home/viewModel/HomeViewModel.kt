package com.markymark.architecture.mvvm.home.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.markymark.architecture.mvvm.home.interactor.HomeColorInteractor
import com.markymark.architecture.mvvm.home.model.ColorPresentationModel
import io.reactivex.disposables.Disposable

class HomeViewModel : ViewModel() {

    private val colorList: MutableLiveData<List<ColorPresentationModel>> = MutableLiveData()
    private val messages: MutableLiveData<String> = MutableLiveData()
    private var disposable: Disposable? = null


    fun getColorList() {
         disposable = HomeColorInteractor().getColors().subscribe { response ->
            if (response.errorMessage != null) {
                messages.value = response.errorMessage
            } else if (response.data != null) {
                colorList.value = response.data
            }
        }
    }

    fun observeColorList() = colorList
    fun observeMessages() = messages

    override fun onCleared() {
        super.onCleared()

        disposable?.dispose()
    }
}