package com.mnhyim.home.news

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.mnhyim.domain.model.articles.ArticleModel
import com.mnhyim.domain.model.sources.SourceModel
import com.mnhyim.domain.usecase.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases
): ViewModel() {


    fun getNews(source: String): Flow<PagingData<ArticleModel>> = newsUseCases.getNewsBySourceUseCase(source)

}