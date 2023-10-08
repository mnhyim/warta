package com.mnhyim.network

import com.mnhyim.network.dto.TopHeadlinesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country: String,
        @Query("page") page: Int,
        @Query("apiKey") apiKey: String = "6aad16b7cb3d4097b8ee45d0eb07a8c1"
    ): TopHeadlinesResponse

}