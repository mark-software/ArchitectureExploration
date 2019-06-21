package com.markymark.architecture.mvi.home.state

import com.markymark.architecture.mvvm.home.model.ColorPresentationModel

interface IHomeViewState {
    class Loading : IHomeViewState
    class Success(val items: List<ColorPresentationModel>?) : IHomeViewState
    class Error(val error: Throwable?) : IHomeViewState
}