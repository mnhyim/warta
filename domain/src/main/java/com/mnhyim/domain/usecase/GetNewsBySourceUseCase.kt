package com.mnhyim.domain.usecase

import androidx.paging.PagingData
import com.mnhyim.domain.model.articles.ArticleModel
import com.mnhyim.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetNewsBySourceUseCase(
    private val repository: NewsRepository,
) {
    operator fun invoke(source: String): Flow<PagingData<ArticleModel>> =
        repository.getNewsBySource(source)
}