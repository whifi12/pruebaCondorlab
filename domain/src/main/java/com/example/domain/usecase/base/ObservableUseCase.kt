package com.example.domain.usecase.base

import com.example.utilities.util.EmptyObserver
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.observers.DisposableObserver
import java.util.concurrent.Executor

/**
 * Abstract class for a Use Case (Interactor in terms of Clean Architecture).
 * This interface represents a execution unit for different use cases (this means any use case
 * in the application should implement this contract).
 *
 * By convention each UseCase implementation will return the result using a [DisposableObserver]
 * that will execute its job in a background thread and will post the result in the UI thread.
 *
 * This use case is to be used when we expect multiple values to be emitted via an [Observable].
 *
 *
 */
abstract class ObservableUseCase<Results, in Params>(
    private val threadExecutor: Scheduler,
    private val postExecutionThread: Scheduler
) : BaseReactiveUseCase() {

    /**
     * Builds an [Observable] which will be used when executing the current [ObservableUseCase].
     */
    abstract fun buildUseCaseObservable(params: Params? = null): Observable<Results>

    /**
     * Executes the current use case.
     *
     * @param observer [DisposableObserver] which will be listening to the observer build
     * by [buildUseCaseObservable] method.
     * @param params Parameters (Optional) used to build/execute this use case.
     */
    fun execute(observer: DisposableObserver<Results> = EmptyObserver(), params: Params? = null) {
        val observable = buildUseCaseObservableWithSchedulers(params)
        addDisposable(observable.subscribeWith(observer))
    }

    /**
     * Builds an [Observable] which will be used when executing the current [ObservableUseCase].
     * With provided Schedulers
     */
    private fun buildUseCaseObservableWithSchedulers(params: Params?): Observable<Results> {
        return buildUseCaseObservable(params)
            .subscribeOn(threadExecutor)
            .observeOn(postExecutionThread)
    }
}
