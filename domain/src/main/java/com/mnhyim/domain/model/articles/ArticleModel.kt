package com.mnhyim.domain.model.articles


data class ArticleModel(
    val author: String,
    val description: String,
    val publishedAt: String,
    val source: String,
    val title: String,
    val url: String,
)