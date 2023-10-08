package com.mnhyim.network.utils

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mnhyim.network.NewsApi
import com.mnhyim.domain.model.ArticleModel
import java.text.SimpleDateFormat
import java.util.Locale

class NewsPagingSource(
    private val newsApi: NewsApi,
) : PagingSource<Int, ArticleModel>() {
    override fun getRefreshKey(state: PagingState<Int, ArticleModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticleModel> {
        return try {
            val page = params.key ?: 1
            val response = newsApi.getCryptoNews(page = page)

            LoadResult.Page(
                data = response.articles.map { article ->
                    ArticleModel(
                        author = article.author ?: "-",
                        content = article.content ?: "-",
                        description = article.description ?: "-",
                        publishedAt = article.publishedAt?.let { convertUTC(it) } ?: "-",
                        source = article.source?.name ?: "-",
                        title = article.title.toString(),
                        url = article.url ?: "-",
                        urlToImage = article.urlToImage ?: "-"
                    )
                },
                prevKey = null,
                nextKey = if (response.articles.isEmpty()) null else page.plus(1),
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}


private fun convertUTC(dateTime: String): String {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
    val date = dateFormat.parse(dateTime)
    val formatter = SimpleDateFormat("dd-MM-yyyy")
    return formatter.format(date)
}
