package com.mnhyim.network.repository

import com.mnhyim.domain.model.ArticleModel
import com.mnhyim.domain.model.TopHeadlinesModel
import com.mnhyim.domain.repository.NewsRepository
import com.mnhyim.network.NewsApi
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsApi: NewsApi
) : NewsRepository {
    override suspend fun getTopHeadlines(): TopHeadlinesModel {
        val result = newsApi.getTopHeadlines(country = "id")
        return TopHeadlinesModel(
            articlesModel = result.articles.map {
                ArticleModel(
                    author = it.author ?: "",
                    title = it.title.toString()
                )
            }
        )
    }
}