package com.sample.musicapp.di

import android.app.Application

class TheApplication : Application() {

    companion object {
        lateinit var component: ApplicationComponent
            private set
    }

    override fun onCreate() {
        super.onCreate()
        component = DaggerApplicationComponent.builder().build()
    }
}