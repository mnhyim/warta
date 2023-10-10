package com.mnhyim.data.remote.dto.sources


import com.mnhyim.data.remote.dto.sources.Source
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SourcesResponse(
    @Json(name = "sources")
    val sources: List<Source>,
    @Json(name = "status")
    val status: String?
)