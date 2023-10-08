package com.mnhyim.home.news

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph

@RootNavGraph(start = true)
@Destination
@Composable
fun NewsScreen(
    viewModel: NewsViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val articles = viewModel.getCryptoNews().collectAsLazyPagingItems()

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(0.dp,8.dp)
    ) {
        items(
            count = articles.itemCount,
            contentType = articles.itemContentType { "Articles" }
        ) { index ->
            articles[index]?.let { article ->
                NewsItem(
                    item = article,
                    modifier = Modifier.padding(8.dp, 0.dp)
                )
            }
        }

        when (val state = articles.loadState.refresh) {
            is LoadState.Error -> {
                Log.d("Paging", "E: ${state.error}")
            }

            is LoadState.Loading -> {
                item {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillParentMaxSize(),
                    ) {
                        CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                    }
                }
            }

            else -> {}
        }

        when (val state = articles.loadState.append) {
            is LoadState.Error -> {
                Log.d("Paging", "E: ${state.error}")
            }

            is LoadState.Loading -> {
                item {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillParentMaxSize(),
                    ) {
                        CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                    }
                }
            }

            else -> {}
        }
    }
}