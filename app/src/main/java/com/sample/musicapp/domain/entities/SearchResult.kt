package com.sample.musicapp.domain.entities

data class SearchResult(
    val resultCount: Int,
    val results: List<SearchResultItem>
)