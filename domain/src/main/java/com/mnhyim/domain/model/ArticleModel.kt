package com.mnhyim.domain.model


data class ArticleModel(
    val author: String,
    val content: Any,
    val description: Any,
    val publishedAt: String,
    val sourceModel: SourceModel,
    val title: String,
    val url: String,
    val urlToImage: Any
)