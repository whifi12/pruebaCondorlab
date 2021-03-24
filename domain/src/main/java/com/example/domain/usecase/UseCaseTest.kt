package com.example.domain.usecase

import com.example.domain.repository.ILeaguesRepositoy
import com.example.utilities.base.BaseReactiveUseCase
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.schedulers.Schedulers


abstract class UseCaseTest<in Params> : BaseReactiveUseCase(){

    /**
     * Builds a [Completable] which will be used when executing the current [CompletableUseCase].
     */
    abstract fun buildUseCaseCompletable(params: Params? = null): Completable

    /**
     * Executes the current use case.
     *
     * @param observer [DisposableCompletableObserver] which will be listening to the observer build
     * by [buildUseCaseCompletable] method.
     * @param params   Parameters (Optional) used to build/execute this use case.
     */
    fun execute(observer: DisposableCompletableObserver, params: Params? = null) {
        val completable = buildUseCaseCompletableWithSchedulers(params)
        addDisposable(completable.subscribeWith(observer))
    }

    private fun buildUseCaseCompletableWithSchedulers(params: Params?): Completable {
        return buildUseCaseCompletable(params)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }



}