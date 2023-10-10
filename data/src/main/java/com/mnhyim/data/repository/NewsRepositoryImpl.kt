package com.mnhyim.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mnhyim.data.local.database.AppDatabase
import com.mnhyim.data.local.entity.ArticleEntity
import com.mnhyim.data.remote.NewsApi
import com.mnhyim.data.remote.paging.NewsPagingSource
import com.mnhyim.data.remote.paging.SearchPagingSource
import com.mnhyim.data.remote.paging.SourcesPagingSource
import com.mnhyim.domain.model.articles.ArticleModel
import com.mnhyim.domain.repository.NewsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsApi: NewsApi,
    private val appDatabase: AppDatabase
) : NewsRepository {
    override fun getSourcesByCategory(category: String) = Pager(
        config = PagingConfig(pageSize = 10),
        pagingSourceFactory = { SourcesPagingSource(newsApi, category) }
    ).flow

    override fun getNewsBySource(source: String): Flow<PagingData<ArticleModel>> = Pager(
        config = PagingConfig(pageSize = 10),
        pagingSourceFactory = { NewsPagingSource(newsApi, source) }
    ).flow

    override fun searchNews(query: String, sources: String): Flow<PagingData<ArticleModel>> = Pager(
        config = PagingConfig(pageSize = 10),
        pagingSourceFactory = { SearchPagingSource(newsApi, query, sources) }
    ).flow

    override fun saveNews(article: ArticleModel) {
        CoroutineScope(IO).launch {
            appDatabase.newsDao().insert(
                ArticleEntity(
                    author = article.author,
                    date = 0,
                    description = article.description,
                    source = article.source,
                    title = article.title,
                    url = article.url,
                )
            )
        }
    }

    override fun getFavoriteNews(): Flow<List<ArticleModel>> {
        return appDatabase.newsDao().getAll().map { favorites ->
            favorites.map { article ->
                ArticleModel(
                    author = article.author ?: "-",
                    publishedAt = "", // TODO: Deal with date stuff
                    description = article.description ?: "-",
                    source = article.source ?: "-",
                    title = article.title ?: "-",
                    url = article.url ?: "-",
                )
            }
        }
    }
}
