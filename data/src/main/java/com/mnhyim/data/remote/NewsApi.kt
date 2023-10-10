package com.mnhyim.data.remote

import com.mnhyim.data.remote.dto.articles.TopHeadlinesResponse
import com.mnhyim.data.remote.dto.sources.SourcesResponse
import com.mnhyim.data.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("top-headlines/sources")
    suspend fun getSources(
        @Query("category") category: String,
        @Query("language") language: String,
        @Query("sortBy") sortBy: String = "popularity",
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int = 10,
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY
    ): SourcesResponse

    @GET("everything")
    suspend fun searchNews(
        @Query("page") page: Int,
        @Query("q") query: String?,
        @Query("sources") sources: String?,
        @Query("pageSize") pageSize: Int = 10,
        @Query("sortBy") sortBy: String = "popularity",
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY
    ): TopHeadlinesResponse

    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("sources") sources: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int = 5,
        @Query("sortBy") sortBy: String = "popularity",
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY
    ): TopHeadlinesResponse
}