package com.mnhyim.home.sources

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.mnhyim.domain.model.sources.SourceModel
import com.mnhyim.domain.usecase.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SourcesViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases
): ViewModel() {

    private var _state = MutableStateFlow(SourcesState())
    val state = _state.asStateFlow()

    fun getSources(): Flow<PagingData<SourceModel>> = newsUseCases.getSourcesByCategoryUseCase(state.value.category)

}