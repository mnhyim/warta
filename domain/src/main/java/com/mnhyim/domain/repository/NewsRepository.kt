package com.mnhyim.domain.repository

import com.mnhyim.domain.model.TopHeadlinesModel

interface NewsRepository {
    suspend fun getTopHeadlines(country: String, page: Int): TopHeadlinesModel
}