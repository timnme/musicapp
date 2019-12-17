package com.sample.musicapp.data.repositories

import com.sample.musicapp.data.DataModelConverter
import com.sample.musicapp.data.network.ITunesApiService
import com.sample.musicapp.domain.entities.SearchResult
import com.sample.musicapp.domain.repositories.SearchRepository
import io.reactivex.Single

class SearchRepositoryImpl(
    private val service: ITunesApiService,
    private val converter: DataModelConverter
) : SearchRepository {
    override fun search(query: String): Single<SearchResult> {
        return service
            .search(query)
            .map(converter::toDomain)
    }
}