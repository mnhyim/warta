package com.mnhyim.domain.usecase

import androidx.paging.PagingData
import com.mnhyim.domain.model.articles.ArticleModel
import com.mnhyim.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetFavoriteNewsUseCase(
    private val repository: NewsRepository,
) {
    operator fun invoke(): Flow<List<ArticleModel>> =
        repository.getFavoriteNews()
}