package com.sample.musicapp.ui.albuminfo

import com.sample.musicapp.domain.usecases.AlbumInfoLoadUseCase

class AlbumInfoPresenter(
    private val albumInfoLoadUseCase: AlbumInfoLoadUseCase
) : AlbumInfoContract.Presenter() {
    override fun loadAlbumInfo(albumId: Int) {
        albumInfoLoadUseCase
            .execute(albumId)
            .register(
                onSuccess = {
                    view.onAlbumInfoLoadComplete(it)
                },
                onError = {
                    view.onAlbumInfoLoadError(it.message)
                }
            )
    }
}