package com.mnhyim.network.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ArticleResponse(
    @Json(name = "author")
    val author: String,
    @Json(name = "content")
    val content: Any,
    @Json(name = "description")
    val description: Any,
    @Json(name = "publishedAt")
    val publishedAt: String,
    @Json(name = "source")
    val source: SourceResponse,
    @Json(name = "title")
    val title: String,
    @Json(name = "url")
    val url: String,
    @Json(name = "urlToImage")
    val urlToImage: Any
)