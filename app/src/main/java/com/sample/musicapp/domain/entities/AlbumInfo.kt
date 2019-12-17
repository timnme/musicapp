package com.sample.musicapp.domain.entities

data class AlbumInfo (
    val name: String,
    val imageUrl: String,
    val authorName: String,
    val songs: List<SongInfo>
)