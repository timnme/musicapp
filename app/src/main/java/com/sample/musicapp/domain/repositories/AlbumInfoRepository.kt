package com.sample.musicapp.domain.repositories

import com.sample.musicapp.domain.entities.AlbumInfo
import io.reactivex.Single

interface AlbumInfoRepository {
    fun load(albumId: Int): Single<AlbumInfo>
}