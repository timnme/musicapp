package com.sample.musicapp.di.modules

import com.sample.musicapp.domain.usecases.AlbumInfoLoadUseCase
import com.sample.musicapp.domain.usecases.SearchUseCase
import com.sample.musicapp.ui.albuminfo.AlbumInfoContract
import com.sample.musicapp.ui.albuminfo.AlbumInfoPresenter
import com.sample.musicapp.ui.search.SearchContract
import com.sample.musicapp.ui.search.SearchPresenter
import dagger.Module
import dagger.Provides

@Module
class UiModule {
    @Provides
    fun provideSearchPresenter(
        searchUseCase: SearchUseCase
    ): SearchContract.Presenter {
        return SearchPresenter(searchUseCase)
    }

    @Provides
    fun provideAlbumInfoPresenter(
        albumInfoLoadUseCase: AlbumInfoLoadUseCase
    ): AlbumInfoContract.Presenter {
        return AlbumInfoPresenter(albumInfoLoadUseCase)
    }
}