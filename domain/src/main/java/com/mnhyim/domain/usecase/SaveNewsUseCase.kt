package com.mnhyim.domain.usecase

import com.mnhyim.domain.model.articles.ArticleModel
import com.mnhyim.domain.repository.NewsRepository

class SaveNewsUseCase(
    private val repository: NewsRepository,
) {
    operator fun invoke(news: ArticleModel) = repository.saveNews(news)
}