package com.markymark.architecture.observer

import io.reactivex.Observer
import io.reactivex.disposables.Disposable

abstract class BaseObserver<T> : Observer<T> {

    override fun onSubscribe(d: Disposable) {}

    abstract override fun onNext(result: T)

    override fun onError(e: Throwable) {}

    override fun onComplete() {}
}
