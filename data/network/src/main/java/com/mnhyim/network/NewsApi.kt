package com.mnhyim.network

import com.mnhyim.network.dto.articles.TopHeadlinesResponse
import com.mnhyim.network.dto.sources.SourcesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    /* TODO: delete later ig */
    @GET("everything")
    suspend fun getCryptoNews(
        @Query("q") country: String = "crypto",
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int = 10,
        @Query("sortBy") sortBy: String = "popularity",
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY
    ): TopHeadlinesResponse

    @GET("top-headlines/sources")
    suspend fun getSources(
        @Query("category") category: String,
        @Query("language") language: String,
        @Query("sortBy") sortBy: String = "popularity",
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int = 10,
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY
    ): SourcesResponse

    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("source") source: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int = 5,
        @Query("sortBy") sortBy: String = "popularity",
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY
    ): TopHeadlinesResponse

    @GET("everything")
    suspend fun searchNews(
        @Query("page") page: Int,
        @Query("q") country: String,
        @Query("pageSize") pageSize: Int = 10,
        @Query("sortBy") sortBy: String = "popularity",
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY
    ): TopHeadlinesResponse
}