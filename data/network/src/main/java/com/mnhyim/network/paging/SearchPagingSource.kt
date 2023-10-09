package com.mnhyim.network.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mnhyim.domain.model.articles.ArticleModel
import com.mnhyim.network.NewsApi
import java.text.SimpleDateFormat
import java.util.Locale

class SearchPagingSource(
    private val newsApi: NewsApi,
    private val query: String,
    private val sources: String
) : PagingSource<Int, ArticleModel>() {
    override fun getRefreshKey(state: PagingState<Int, ArticleModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: PagingSource.LoadParams<Int>): PagingSource.LoadResult<Int, ArticleModel> {
        return try {
            val page = params.key ?: 1
            val response = newsApi.searchNews(query = query, sources = sources, page = page)

            PagingSource.LoadResult.Page(
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
            PagingSource.LoadResult.Error(e)
        }
    }
}

private fun convertUTC(dateTime: String): String {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
    val date = dateFormat.parse(dateTime)
    val formatter = SimpleDateFormat("EE, dd MMM yyyy")
    return formatter.format(date)
}