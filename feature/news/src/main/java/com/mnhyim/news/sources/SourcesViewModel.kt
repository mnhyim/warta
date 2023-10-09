package com.mnhyim.news.sources

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.mnhyim.domain.model.sources.SourceModel
import com.mnhyim.domain.usecase.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class SourcesViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases
) : ViewModel() {

    fun getSources(category: String): Flow<PagingData<SourceModel>> =
        newsUseCases.getSourcesByCategoryUseCase(category)

}