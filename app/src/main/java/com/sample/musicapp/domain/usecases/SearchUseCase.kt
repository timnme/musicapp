package com.sample.musicapp.domain.usecases

import com.sample.musicapp.domain.entities.SearchResult
import com.sample.musicapp.domain.repositories.SearchRepository
import io.reactivex.Single

interface SearchUseCase {
    fun execute(query: String): Single<SearchResult>
}

class SearchUseCaseImpl(
    private val repository: SearchRepository
) : SearchUseCase {
    override fun execute(query: String): Single<SearchResult> {
        return repository.search(query).map {
            /**
             * Copy of the search result with albums sorted alphabetically
             */
            it.copy(results = it.results.sortedWith(
                Comparator { album1, album2 ->
                    album1.albumName.compareTo(album2.albumName, true)
                }
            ))
        }
    }
}