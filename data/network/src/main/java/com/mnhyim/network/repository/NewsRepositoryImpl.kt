package com.mnhyim.network.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mnhyim.domain.model.articles.ArticleModel
import com.mnhyim.domain.repository.NewsRepository
import com.mnhyim.network.NewsApi
import com.mnhyim.network.paging.NewsPagingSource
import com.mnhyim.network.paging.SourcesPagingSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsApi: NewsApi
) : NewsRepository {
    override fun getSourcesByCategory(category: String) = Pager(
        config = PagingConfig(pageSize = 10),
        pagingSourceFactory = { SourcesPagingSource(newsApi, category) }
    ).flow

    override fun getNewsBySource(source: String): Flow<PagingData<ArticleModel>> = Pager(
        config = PagingConfig(pageSize = 10),
        pagingSourceFactory = { NewsPagingSource(newsApi, source) }
    ).flow
}
