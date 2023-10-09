package com.mnhyim.network.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mnhyim.domain.model.sources.SourceModel
import com.mnhyim.network.NewsApi

class SourcesPagingSource(
    private val newsApi: NewsApi,
    private val category: String
) : PagingSource<Int, SourceModel>() {
    override fun getRefreshKey(state: PagingState<Int, SourceModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: PagingSource.LoadParams<Int>): PagingSource.LoadResult<Int, SourceModel> {
        return try {
            val page = params.key ?: 1
            val response = newsApi.getSources(category = category, language = "en", page = page)

            PagingSource.LoadResult.Page(
                data = response.sources.map { source ->
                    SourceModel(
                        category = source.category ?: "-",
                        country = source.country ?: "-",
                        description = source.description ?: "-",
                        id = source.id ?: "-",
                        language = source.language ?: "-",
                        name = source.name ?: "-",
                        url = source.url ?: "-"
                    )
                },
                prevKey = null,
                nextKey = if (response.sources.isEmpty()) null else page.plus(1),
            )
        } catch (e: Exception) {
            PagingSource.LoadResult.Error(e)
        }
    }
}