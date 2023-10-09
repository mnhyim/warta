package com.mnhyim.network

import com.mnhyim.network.dto.articles.TopHeadlinesResponse
import com.mnhyim.network.dto.sources.SourcesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {


    @GET("everything")
    suspend fun getCryptoNews(
        @Query("q") country: String = "crypto",
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int = 10,
        @Query("sortBy") sortBy: String = "popularity",
        @Query("apiKey") apiKey: String = "e05e69123d064fb4a0b6377b73927357"
    ): TopHeadlinesResponse

    @GET("top-headlines/sources")
    suspend fun getSources(
        @Query("category") category: String,
        @Query("language") language: String,
        @Query("sortBy") sortBy: String = "popularity",
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int = 10,
        @Query("apiKey") apiKey: String = "e05e69123d064fb4a0b6377b73927357"
    ): SourcesResponse

    @GET("everything")
    suspend fun searchNews(
        @Query("page") page: Int,
        @Query("q") country: String,
        @Query("pageSize") pageSize: Int = 10,
        @Query("sortBy") sortBy: String = "popularity",
        @Query("apiKey") apiKey: String = "e05e69123d064fb4a0b6377b73927357"
    ): TopHeadlinesResponse

//    @GET("top-headlines")
//    suspend fun getTopHeadlines(
//        @Query("page") page: Int,
//        @Query("country") country: String = "id",
//        @Query("pageSize") pageSize: Int = 5,
//        @Query("sortBy=popularity") sortBy: String = "popularity",
//        @Query("apiKey") apiKey: String = "6aad16b7cb3d4097b8ee45d0eb07a8c1"
//    ): TopHeadlinesResponse

}