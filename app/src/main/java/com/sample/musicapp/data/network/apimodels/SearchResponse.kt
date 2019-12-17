package com.sample.musicapp.data.network.apimodels

import com.google.gson.annotations.SerializedName

data class SearchResponse(

	@field:SerializedName("resultCount")
	val resultCount: Int? = null,

	@field:SerializedName("results")
	val results: List<SearchItem?>? = null
)