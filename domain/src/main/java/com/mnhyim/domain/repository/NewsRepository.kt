package com.mnhyim.domain.repository

import androidx.paging.PagingData
import com.mnhyim.domain.model.articles.ArticleModel
import com.mnhyim.domain.model.sources.SourceModel
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun getCryptoNews(): Flow<PagingData<ArticleModel>>
    fun getSourcesByCategory(category: String): Flow<PagingData<SourceModel>>
}