package com.sample.musicapp.data

import com.sample.musicapp.data.network.apimodels.AlbumInfoItem
import com.sample.musicapp.data.network.apimodels.AlbumInfoResponse
import com.sample.musicapp.data.network.apimodels.SearchItem
import com.sample.musicapp.data.network.apimodels.SearchResponse
import com.sample.musicapp.domain.entities.AlbumInfo
import com.sample.musicapp.domain.entities.SearchResult
import com.sample.musicapp.domain.entities.SearchResultItem
import com.sample.musicapp.domain.entities.SongInfo

/**
 * Convert data retrieved from iTunes servers to domain form
 */
interface DataModelConverter {
    fun toDomain(response: SearchResponse): SearchResult
    fun toDomain(response: AlbumInfoResponse): AlbumInfo
}

class DataModelConverterImpl : DataModelConverter {
    override fun toDomain(response: SearchResponse): SearchResult {
        return with(response) {
            SearchResult(
                resultCount = resultCount ?: 0,
                results = searchItemsToDomain(results)
            )
        }
    }

    private fun searchItemsToDomain(items: List<SearchItem?>?): List<SearchResultItem> {
        return if (items.isNullOrEmpty()) emptyList()
        else {
            items.map {
                SearchResultItem(
                    albumId = it?.collectionId ?: 0,
                    albumName = it?.collectionName ?: ""
                )
            }
        }
    }

    override fun toDomain(response: AlbumInfoResponse): AlbumInfo {
        return with(response) {
            AlbumInfo(
                authorName = results?.firstOrNull()?.artistName ?: "",
                imageUrl = results?.firstOrNull()?.artworkUrl100 ?: "",
                name = results?.firstOrNull()?.collectionName ?: "",
                songs = songsToDomain(results)
            )
        }
    }

    private fun songsToDomain(items: List<AlbumInfoItem?>?): List<SongInfo> {
        return if (items.isNullOrEmpty()) emptyList()
        else {
            if (items.size > 1) {
                // In data retrieved from the iTunes server the first item is the album info,
                // so we remove it from the list
                val songs = items.subList(1, items.size - 1)
                songs.map {
                    SongInfo(
                        trackNumber = it?.trackNumber ?: 0,
                        name = it?.trackName ?: ""
                    )
                }
            } else emptyList()
        }
    }
}

