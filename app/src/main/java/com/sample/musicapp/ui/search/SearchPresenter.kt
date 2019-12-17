package com.sample.musicapp.ui.search

import com.sample.musicapp.domain.usecases.SearchUseCase

class SearchPresenter(
    private val searchUseCase: SearchUseCase
) : SearchContract.Presenter() {
    override fun search(query: String) {
        searchUseCase
            .execute(query)
            .register(
                onSuccess = {
                    view.onSearchComplete(it.results)
                },
                onError = {
                    view.onSearchError(it.message)
                }
            )
    }
}