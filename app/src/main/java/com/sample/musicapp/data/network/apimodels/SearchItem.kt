package com.sample.musicapp.data.network.apimodels

import com.google.gson.annotations.SerializedName

data class SearchItem(

	@field:SerializedName("artworkUrl100")
	val artworkUrl100: String? = null,

	@field:SerializedName("country")
	val country: String? = null,

	@field:SerializedName("copyright")
	val copyright: String? = null,

	@field:SerializedName("collectionViewUrl")
	val collectionViewUrl: String? = null,

	@field:SerializedName("releaseDate")
	val releaseDate: String? = null,

	@field:SerializedName("artistId")
	val artistId: Int? = null,

	@field:SerializedName("collectionPrice")
	val collectionPrice: Double? = null,

	@field:SerializedName("primaryGenreName")
	val primaryGenreName: String? = null,

	@field:SerializedName("collectionName")
	val collectionName: String? = null,

	@field:SerializedName("artistViewUrl")
	val artistViewUrl: String? = null,

	@field:SerializedName("collectionType")
	val collectionType: String? = null,

	@field:SerializedName("collectionExplicitness")
	val collectionExplicitness: String? = null,

	@field:SerializedName("trackCount")
	val trackCount: Int? = null,

	@field:SerializedName("artworkUrl60")
	val artworkUrl60: String? = null,

	@field:SerializedName("wrapperType")
	val wrapperType: String? = null,

	@field:SerializedName("artistName")
	val artistName: String? = null,

	@field:SerializedName("currency")
	val currency: String? = null,

	@field:SerializedName("collectionId")
	val collectionId: Int? = null,

	@field:SerializedName("collectionCensoredName")
	val collectionCensoredName: String? = null,

	@field:SerializedName("amgArtistId")
	val amgArtistId: Int? = null,

	@field:SerializedName("contentAdvisoryRating")
	val contentAdvisoryRating: String? = null
)