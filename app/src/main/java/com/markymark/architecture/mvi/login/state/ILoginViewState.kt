package com.markymark.architecture.mvi.login.state

interface ILoginViewState {
    class NotStarted : ILoginViewState
    class Loading : ILoginViewState
    class Success : ILoginViewState
    class Error(val error: Throwable?) : ILoginViewState
}