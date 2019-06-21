package com.markymark.architecture.mvp.home.view

import com.hannesdorfmann.mosby3.mvp.MvpView
import com.markymark.architecture.mvvm.home.model.ColorPresentationModel

interface IHomeView: MvpView {
    fun setAdapterItems(items: List<ColorPresentationModel>)
    fun showToast(text: String)
}