package com.sample.musicapp.data.network

import com.sample.musicapp.data.network.apimodels.AlbumInfoResponse
import com.sample.musicapp.data.network.apimodels.SearchResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Retrieve data from iTunes servers
 */
interface ITunesApiService {
    /**
     * Search albums for the given [term]
     */
    @GET("search?")
    fun search(
        @Query("term") term: String,
        @Query("media") media: String = "music",
        @Query("entity") entity: String = "album",
        @Query("attribute") attribute: String = "albumTerm"
    ): Single<SearchResponse>

    /**
     * Get album info by [id] and its songs
     */
    @GET("lookup?")
    fun loadAlbumInfo(
        @Query("id") id: Int,
        @Query("entity") entity: String = "song"
    ): Single<AlbumInfoResponse>
}