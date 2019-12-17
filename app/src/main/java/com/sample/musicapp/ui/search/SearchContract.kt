package com.sample.musicapp.ui.search

import com.sample.musicapp.domain.entities.SearchResultItem
import com.sample.musicapp.mvp.BaseMvpPresenter
import com.sample.musicapp.mvp.MvpView

interface SearchContract {
    interface View : MvpView {
        fun onSearchComplete(results: List<SearchResultItem>)
        fun onSearchError(errMessage: String?)
    }

    abstract class Presenter : BaseMvpPresenter<View>() {
        abstract fun search(query: String)
    }
}