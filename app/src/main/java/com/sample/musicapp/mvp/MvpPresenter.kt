package com.sample.musicapp.mvp

interface MvpPresenter<V : MvpView> {
    fun onViewAttached(view: V)
    fun onViewDetached()
}