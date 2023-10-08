package com.mnhyim.home.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mnhyim.domain.usecase.NewsUseCases
import com.mnhyim.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases
) : ViewModel() {

    private var _state = MutableStateFlow(NewsState())
    val state = _state.asStateFlow()

    init {
        getTopHeadlines()
    }

    fun getTopHeadlines() {
        newsUseCases.getTopHeadlines(
            country = "id",
            page = state.value.currentPage
        ).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    result.content?.let { topHeadlines ->
                        _state.update {
                            it.copy(isLoading = false, articles = topHeadlines.articlesModel)
                        }
                    }
                }
                is Resource.Loading -> _state.update { it.copy(isLoading = true) }
                is Resource.Error -> _state.update { it.copy(isLoading = false) }
            }
        }.launchIn(viewModelScope)
    }
}