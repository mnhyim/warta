package com.mnhyim.network.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.mnhyim.domain.repository.NewsRepository
import com.mnhyim.network.NewsApi
import com.mnhyim.network.utils.NewsPagingSource
import com.mnhyim.network.utils.SourcesPagingSource
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsApi: NewsApi
) : NewsRepository {
    override fun getCryptoNews() = Pager(
        config = PagingConfig(pageSize = 10),
        pagingSourceFactory = { NewsPagingSource(newsApi) }
    ).flow

    override fun getSourcesByCategory(category: String) = Pager(
        config = PagingConfig(pageSize = 10),
        pagingSourceFactory = { SourcesPagingSource(newsApi, category) }
    ).flow
}
