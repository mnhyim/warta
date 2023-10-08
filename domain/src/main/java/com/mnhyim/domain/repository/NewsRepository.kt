package com.mnhyim.domain.repository

import androidx.paging.PagingData
import com.mnhyim.domain.model.ArticleModel
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun getCryptoNews(): Flow<PagingData<ArticleModel>>
}