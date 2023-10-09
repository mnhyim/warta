package com.mnhyim.domain.usecase

data class NewsUseCases(
    val getCryptoNews: GetCryptoNews,
    val getSourcesByCategoryUseCase: GetSourcesByCategoryUseCase
)