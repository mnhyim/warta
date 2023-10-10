package com.mnhyim.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.room.Database
import com.mnhyim.data.local.dao.NewsDao
import com.mnhyim.data.local.database.AppDatabase
import com.mnhyim.data.local.entity.ArticleEntity
import com.mnhyim.domain.model.articles.ArticleModel
import com.mnhyim.domain.repository.NewsRepository
import com.mnhyim.data.remote.NewsApi
import com.mnhyim.data.remote.paging.NewsPagingSource
import com.mnhyim.data.remote.paging.SearchPagingSource
import com.mnhyim.data.remote.paging.SourcesPagingSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
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

    override fun saveNews(news: ArticleModel) {
        CoroutineScope(IO).launch {
            appDatabase.newsDao().insert(
                ArticleEntity(
                    author = news.author,
                    date = 0,
                    description = news.description,
                    source = news.source,
                    title = news.title,
                    url = news.url,
                )
            )
        }
    }
}
