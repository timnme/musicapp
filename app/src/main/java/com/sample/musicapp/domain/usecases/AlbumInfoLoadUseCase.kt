package com.sample.musicapp.domain.usecases

import com.sample.musicapp.domain.entities.AlbumInfo
import com.sample.musicapp.domain.repositories.AlbumInfoRepository
import io.reactivex.Single

interface AlbumInfoLoadUseCase {
    fun execute(albumId: Int): Single<AlbumInfo>
}

class AlbumInfoLoadUseCaseImpl(
    private val repository: AlbumInfoRepository
) : AlbumInfoLoadUseCase {
    override fun execute(albumId: Int): Single<AlbumInfo> {
        return repository.load(albumId).map {
            /**
             * Copy of the album with songs sorted by number
             */
            it.copy(songs = it.songs.sortedWith(
                Comparator { song1, song2 ->
                    song1.trackNumber.compareTo(song2.trackNumber)
                }
            ))
        }
    }
}