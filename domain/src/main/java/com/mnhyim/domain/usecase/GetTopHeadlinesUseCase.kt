package com.mnhyim.domain.usecase

import com.mnhyim.domain.model.TopHeadlinesModel
import com.mnhyim.domain.repository.NewsRepository
import com.mnhyim.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetTopHeadlinesUseCase(
    private val repository: NewsRepository
) {
    operator fun invoke(): Flow<Resource<TopHeadlinesModel>> = flow {
        try {
            emit(Resource.Loading())
            val result = repository.getTopHeadlines()
            emit(Resource.Success(result))
        } catch (e: Exception) {
            emit(Resource.Error("Exception - ${e.localizedMessage}"))
        } catch (e: IOException) {
            emit(Resource.Error("IOException - ${e.localizedMessage}"))
        }
    }
}