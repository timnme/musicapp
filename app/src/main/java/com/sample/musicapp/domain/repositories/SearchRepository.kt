package com.sample.musicapp.domain.repositories

import com.sample.musicapp.domain.entities.SearchResult
import io.reactivex.Single

interface SearchRepository {
    fun search(query: String): Single<SearchResult>
}