package com.mnhyim.domain.usecase

data class NewsUseCases(
    val getSourcesByCategoryUseCase: GetSourcesByCategoryUseCase,
    val getNewsBySourceUseCase: GetNewsBySourceUseCase,
    val searchNewsUseCase: SearchNewsUseCase,
    val saveNewsUseCase: SaveNewsUseCase
)