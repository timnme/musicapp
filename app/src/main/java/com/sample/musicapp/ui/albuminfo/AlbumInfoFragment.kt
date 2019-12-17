package com.sample.musicapp.ui.albuminfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.sample.musicapp.R
import com.sample.musicapp.di.TheApplication
import com.sample.musicapp.domain.entities.AlbumInfo
import com.sample.musicapp.mvp.MvpFragment
import com.sample.musicapp.ui.albuminfo.adapters.SongsAdapter
import kotlinx.android.synthetic.main.fragment_album_info.*
import javax.inject.Inject

class AlbumInfoFragment : MvpFragment<AlbumInfoContract.View, AlbumInfoContract.Presenter>(),
    AlbumInfoContract.View {

    @Inject
    override lateinit var presenter: AlbumInfoContract.Presenter
    private lateinit var songsAdapter: SongsAdapter

    companion object {
        private const val ALBUM_ID: String = "ALBUM_ID"

        fun newInstance(albumId: Int) = AlbumInfoFragment().apply {
            arguments = Bundle().apply { putInt(ALBUM_ID, albumId) }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        TheApplication.component.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = inflater.inflate(
        R.layout.fragment_album_info, container, false
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        loadAlbumInfo()
    }

    private fun setupAdapter() {
        songsAdapter = SongsAdapter()
        songs_RecyclerView.adapter = songsAdapter
    }

    private fun loadAlbumInfo() {
        val albumId = arguments?.getInt(ALBUM_ID)
        if (albumId != null) {
            presenter.loadAlbumInfo(albumId)
        } else {
            songs_RecyclerView.visibility = View.GONE
            message_TextView.visibility = View.VISIBLE
            message_TextView.text = getString(R.string.error)
        }
    }

    override fun onAlbumInfoLoadComplete(result: AlbumInfo) {
        // Load album image
        with(albumImage_ImageView) {
            Glide.with(context)
                .load(result.imageUrl)
                .into(this)
        }
        // Set album info
        authorName_TextView.text = String.format(getString(R.string.title_author), result.authorName)
        albumName_TextView.text = String.format(getString(R.string.title_album), result.name)

        if (result.songs.isNotEmpty()) {
            // Show songs
            songs_RecyclerView.visibility = View.VISIBLE
            message_TextView.visibility = View.GONE
            songsAdapter.onNewItems(result.songs)
        } else {
            // No songs, show message and hide the recycler view
            songs_RecyclerView.visibility = View.GONE
            message_TextView.visibility = View.VISIBLE
            message_TextView.text = getString(R.string.msg_no_songs)
        }
    }

    override fun onAlbumInfoLoadError(errMessage: String?) {
        songs_RecyclerView.visibility = View.GONE
        message_TextView.visibility = View.VISIBLE
        message_TextView.text = errMessage ?: getString(R.string.error)
    }
}