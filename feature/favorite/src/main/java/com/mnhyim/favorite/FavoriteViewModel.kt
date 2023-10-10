package com.mnhyim.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mnhyim.domain.model.articles.ArticleModel
import com.mnhyim.domain.usecase.NewsUseCases
import com.mnhyim.domain.usecase.SaveNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val newsUseCase: NewsUseCases
) : ViewModel() {

    var _favorites = MutableStateFlow<List<ArticleModel>>(emptyList())
    val favorites = _favorites.asStateFlow()

    init {
        getFavoriteNews()
    }

    private fun getFavoriteNews() {
        viewModelScope.launch {
            _favorites.update { newsUseCase.getFavoriteNews().first() }
        }
    }
}