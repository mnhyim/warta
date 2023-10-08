package com.mnhyim.network.repository

import com.mnhyim.domain.model.ArticleModel
import com.mnhyim.domain.model.TopHeadlinesModel
import com.mnhyim.domain.repository.NewsRepository
import com.mnhyim.network.NewsApi
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsApi: NewsApi
) : NewsRepository {
    override suspend fun getTopHeadlines(country: String, page: Int): TopHeadlinesModel {
        val result = newsApi.getTopHeadlines(
            country = country,
            page = page
        )
        return TopHeadlinesModel(
            articlesModel = result.articles.map { article ->
                ArticleModel(
                    author = article.author ?: "",
                    content = article.content ?: "",
                    description = article.description ?: "",
                    publishedAt = article.publishedAt?.let { convertUTC(it) } ?: "",
                    title = article.title.toString(),
                    url = article.url ?: "",
                    urlToImage = article.urlToImage ?: ""
                )
            }
        )
    }
}

private fun convertUTC(dateTime: String): String {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
    val date = dateFormat.parse(dateTime)
    val formatter = SimpleDateFormat("dd-MM-yyyy")
    return formatter.format(date)
}
