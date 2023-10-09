package com.mnhyim.home.search

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.mnhyim.domain.model.articles.ArticleModel
import com.mnhyim.domain.usecase.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases
) : ViewModel() {

    private var _search = MutableStateFlow("")
    val search = _search.asStateFlow()

    private var _searchMode = MutableStateFlow(false)
    val searchMode = _searchMode.asStateFlow()

    fun changeSearchQuery(query: String) = _search.update { query }
    fun changeSearchMode(boolean: Boolean) = _searchMode.update { boolean }

    fun searchNews(query: String): Flow<PagingData<ArticleModel>> =
        search.debounce(800).flatMapLatest {
            if (searchMode.value) {
                newsUseCases.searchNewsUseCase(query = "", sources = query)
            } else {
                newsUseCases.searchNewsUseCase(query = query, sources = "")
            }
        }

}