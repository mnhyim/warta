package com.mnhyim.home.news

import com.mnhyim.domain.model.ArticleModel

data class NewsState(
    val isLoading: Boolean = true,
    val articles: List<ArticleModel> = emptyList()
)
