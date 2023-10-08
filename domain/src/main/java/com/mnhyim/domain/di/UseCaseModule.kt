package com.mnhyim.domain.di

import com.mnhyim.domain.repository.NewsRepository
import com.mnhyim.domain.usecase.GetTopHeadlinesUseCase
import com.mnhyim.domain.usecase.NewsUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @ViewModelScoped
    @Provides
    fun provideNewsUseCases(repository: NewsRepository): NewsUseCases {
        return NewsUseCases(
            getTopHeadlines = GetTopHeadlinesUseCase(repository)
        )
    }
}