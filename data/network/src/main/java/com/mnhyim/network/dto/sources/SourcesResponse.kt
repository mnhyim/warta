package com.mnhyim.network.dto.sources


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SourcesResponse(
    @Json(name = "sources")
    val sources: List<Source>,
    @Json(name = "status")
    val status: String?
)