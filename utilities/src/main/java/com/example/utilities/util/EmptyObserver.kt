package com.example.utilities.util

import io.reactivex.annotations.NonNull
import io.reactivex.observers.DisposableObserver

open class EmptyObserver<T> : DisposableObserver<T>() {

    override fun onNext(@NonNull result: T) {}

    override fun onError(@NonNull error: Throwable) {}

    override fun onComplete() {}
}