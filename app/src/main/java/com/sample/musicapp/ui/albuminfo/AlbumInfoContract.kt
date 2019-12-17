package com.sample.musicapp.ui.albuminfo

import com.sample.musicapp.domain.entities.AlbumInfo
import com.sample.musicapp.mvp.BaseMvpPresenter
import com.sample.musicapp.mvp.MvpView

interface AlbumInfoContract {
    interface View : MvpView {
        fun onAlbumInfoLoadComplete(result: AlbumInfo)
        fun onAlbumInfoLoadError(errMessage: String?)
    }

    abstract class Presenter : BaseMvpPresenter<View>() {
        abstract fun loadAlbumInfo(albumId: Int)
    }
}