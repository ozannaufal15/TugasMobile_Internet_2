package com.example.android.tugasmobile.network

import androidx.annotation.Nullable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Article (
    val id: String,
    val title: String,
    val abstract: String,
    @Json(name = "url")val articleUrl: String,
    @Json(name = "published_date") val publishedDate: String,
    @Json(name="media") val media: List<Media>
    )

@JsonClass(generateAdapter = true)
data class Media (
    val type: String,
    val caption: String,
    val copyright: String,
    @Json(name="media-metadata") val mediaMetaData: List<MediaMetaData>
    )

@JsonClass(generateAdapter = true)
data class MediaMetaData (
    @Json(name = "url") val ImageUrl: String,
    val height: Int,
    val width: Int
    )

@JsonClass(generateAdapter = true)
data class ArticleResponse (
    @Json(name = "results") val result: List<Article>
    )