package com.sample.musicapp.ui

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.sample.musicapp.R
import com.sample.musicapp.di.TheApplication
import com.sample.musicapp.ui.albuminfo.AlbumInfoFragment
import com.sample.musicapp.ui.search.SearchFragment

class MainActivity : AppCompatActivity(), SearchFragment.OnAlbumSelectedListener {

    private val searchFragment: SearchFragment by lazy { SearchFragment.newInstance() }

    override fun onCreate(savedInstanceState: Bundle?) {
        TheApplication.component.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        // Load search fragment
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, searchFragment)
            .commit()
    }

    override fun onAlbumSelected(albumId: Int) {
        // Load album info fragment
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, AlbumInfoFragment.newInstance(albumId))
            .addToBackStack(null)
            .commit()
    }
}