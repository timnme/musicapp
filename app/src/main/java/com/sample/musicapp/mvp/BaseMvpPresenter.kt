package com.sample.musicapp.mvp

import android.util.Log
import com.sample.musicapp.BuildConfig
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Base class for presenters from MVP pattern
 */
abstract class BaseMvpPresenter<V : MvpView> : MvpPresenter<V> {
    private val referencesHolder = ResettableReferences()

    private lateinit var compositeDisposable: CompositeDisposable
    protected var view: V by Resettable(referencesHolder)

    /**
     * Assign view and init [compositeDisposable] on view creation
     */
    override fun onViewAttached(view: V) {
        this.view = view
        compositeDisposable = CompositeDisposable()
    }

    /**
     * Nullify the [view] and clear [compositeDisposable] on view destruction
     * to avoid memory leaks
     */
    override fun onViewDetached() {
        referencesHolder.reset()
        compositeDisposable.clear()
    }

    protected fun <T> Single<T>.register(
        onSuccess: (T) -> Unit, onError: (Throwable) -> Unit
    ) = this
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSuccess { log(it) }
        .doOnError { log(it) }
        .subscribe(onSuccess::invoke, onError::invoke)
        .register()

    /**
     * Log success
     */
    private fun <T> log(t: T) {
        if (BuildConfig.DEBUG) Log.d("APP_LOGS", t.toString())
    }

    /**
     * Log errors
     */
    private fun log(t: Throwable) {
        if (BuildConfig.DEBUG) Log.d("APP_LOGS", "Error", t)
    }

    private fun Disposable.register() {
        compositeDisposable.add(this)
    }
}