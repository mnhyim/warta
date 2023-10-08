package com.mnhyim.network.repository

import com.mnhyim.domain.model.TopHeadlinesModel
import com.mnhyim.domain.repository.NewsRepository
import com.mnhyim.network.NewsApi
import timber.log.Timber
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsApi: NewsApi
) : NewsRepository {
    override suspend fun getTopHeadlines(): String {
//        val result = newsApi.getTopHeadlines(country = "id")
        return "success"
    }
}