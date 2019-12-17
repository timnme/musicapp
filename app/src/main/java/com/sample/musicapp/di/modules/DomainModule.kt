package com.sample.musicapp.di.modules

import com.sample.musicapp.domain.repositories.AlbumInfoRepository
import com.sample.musicapp.domain.repositories.SearchRepository
import com.sample.musicapp.domain.usecases.AlbumInfoLoadUseCase
import com.sample.musicapp.domain.usecases.AlbumInfoLoadUseCaseImpl
import com.sample.musicapp.domain.usecases.SearchUseCase
import com.sample.musicapp.domain.usecases.SearchUseCaseImpl
import dagger.Module
import dagger.Provides

@Module
class DomainModule {
    @Provides
    fun provideDomainsUseCase(
        searchRepository: SearchRepository
    ): SearchUseCase {
        return SearchUseCaseImpl(searchRepository)
    }

    @Provides
    fun provideAlbumInfoLoadUseCase(
        albumInfoRepository: AlbumInfoRepository
    ): AlbumInfoLoadUseCase {
        return AlbumInfoLoadUseCaseImpl(albumInfoRepository)
    }
}