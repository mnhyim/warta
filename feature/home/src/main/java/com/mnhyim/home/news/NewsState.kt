package com.mnhyim.home.news

import com.mnhyim.domain.model.articles.ArticleModel

data class NewsState(
    val isLoading: Boolean = true,
    val currentPage: Int = 1,
    val articles: List<ArticleModel> = emptyList()
)
