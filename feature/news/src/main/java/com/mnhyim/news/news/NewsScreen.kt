package com.mnhyim.news.news

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import com.mnhyim.news.destinations.OpenWebViewDestination
import com.mnhyim.ui.components.NewsCardItem
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun NewsScreen(
    navigator: DestinationsNavigator,
    newsSourceId: String,
    newsSourceName: String,
    newsSourceDescription: String,
    viewModel: NewsViewModel = hiltViewModel()
) {

    val news = viewModel.getNews(newsSourceId).collectAsLazyPagingItems()

    Column {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .clip(RoundedCornerShape(0.dp, 0.dp, 16.dp, 16.dp))
                .background(
                    MaterialTheme.colorScheme.primary,
                    RoundedCornerShape(0.dp, 0.dp, 16.dp, 16.dp)
                )
        ) {
            Text(
                text = newsSourceName,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onPrimary,
                fontFamily = FontFamily.Serif,
                modifier = Modifier.padding(24.dp)
            )
            Text(
                text = newsSourceDescription,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.tertiary.copy(alpha = 0.25f))
                    .padding(12.dp)
            )
        }
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            verticalItemSpacing = 8.dp,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .padding(8.dp)
                .fillMaxSize()
        ) {
            items(
                count = news.itemCount,
                contentType = news.itemContentType { "Articles" }
            ) { index ->
                news[index]?.let { article ->
                    NewsCardItem(
                        articleUrl = article.url,
                        articleTitle = article.title,
                        articlePublishedAt = article.publishedAt,
                        articleDescription = article.description,
                        onClick = {
                            navigator.navigate(OpenWebViewDestination(it))
                        }
                    )
                }
            }

            when (val state = news.loadState.refresh) {
                is LoadState.Error -> Log.d("Paging", "E: ${state.error}")

                is LoadState.Loading -> {
                    item {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.fillMaxWidth(),
                        ) {
                            CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                        }
                    }
                }

                else -> {}
            }

            when (val state = news.loadState.append) {
                is LoadState.Error -> Log.d("Paging", "E: ${state.error}")

                is LoadState.Loading -> {
                    item {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.fillMaxWidth(),
                        ) {
                            CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                        }
                    }
                }

                else -> {}
            }
        }
    }
}