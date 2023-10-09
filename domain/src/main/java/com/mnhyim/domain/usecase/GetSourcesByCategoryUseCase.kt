package com.mnhyim.domain.usecase

import androidx.paging.PagingData
import com.mnhyim.domain.model.articles.ArticleModel
import com.mnhyim.domain.model.sources.SourceModel
import com.mnhyim.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetSourcesByCategoryUseCase(
    private val repository: NewsRepository,
) {
    operator fun invoke(category: String): Flow<PagingData<SourceModel>> = repository.getSourcesByCategory(category)
}