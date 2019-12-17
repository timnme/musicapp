package com.sample.musicapp.di.modules

import com.sample.musicapp.data.DataModelConverter
import com.sample.musicapp.data.DataModelConverterImpl
import com.sample.musicapp.data.network.ITunesApiService
import com.sample.musicapp.data.repositories.AlbumInfoRepositoryImpl
import com.sample.musicapp.data.repositories.SearchRepositoryImpl
import com.sample.musicapp.domain.repositories.AlbumInfoRepository
import com.sample.musicapp.domain.repositories.SearchRepository
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class DataModule {
    @Provides
    @Singleton
    fun provideDataModelConverter(): DataModelConverter {
        return DataModelConverterImpl()
    }

    @Provides
    @Singleton
    fun provideComponentRepository(
        service: ITunesApiService,
        converter: DataModelConverter
    ): SearchRepository {
        return SearchRepositoryImpl(service, converter)
    }

    @Provides
    @Singleton
    fun provideAlbumInfoRepository(
        service: ITunesApiService,
        converter: DataModelConverter
    ): AlbumInfoRepository {
        return AlbumInfoRepositoryImpl(service, converter)
    }

    @Provides
    @Singleton
    fun provideApiService(): ITunesApiService {
        return Retrofit.Builder()
            .baseUrl("https://itunes.apple.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(OkHttpClient.Builder().build())
            .build()
            .create(ITunesApiService::class.java)
    }
}