package com.sample.musicapp.di

import com.sample.musicapp.di.modules.*
import com.sample.musicapp.ui.MainActivity
import com.sample.musicapp.ui.albuminfo.AlbumInfoFragment
import com.sample.musicapp.ui.search.SearchFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        DataModule::class,
        DomainModule::class,
        UiModule::class
    ]
)
interface ApplicationComponent {
    fun inject(activity: MainActivity)
    fun inject(fragment: SearchFragment)
    fun inject(fragment: AlbumInfoFragment)
}