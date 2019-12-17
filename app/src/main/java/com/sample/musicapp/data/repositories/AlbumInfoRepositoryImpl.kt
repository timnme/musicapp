package com.sample.musicapp.data.repositories

import com.sample.musicapp.data.DataModelConverter
import com.sample.musicapp.data.network.ITunesApiService
import com.sample.musicapp.domain.entities.AlbumInfo
import com.sample.musicapp.domain.repositories.AlbumInfoRepository
import io.reactivex.Single

class AlbumInfoRepositoryImpl(
    private val service: ITunesApiService,
    private val converter: DataModelConverter
) : AlbumInfoRepository {
    override fun load(albumId: Int): Single<AlbumInfo> {
        return service
            .loadAlbumInfo(albumId)
            .map(converter::toDomain)
    }
}