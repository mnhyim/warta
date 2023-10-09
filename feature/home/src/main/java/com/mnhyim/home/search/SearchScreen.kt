package com.mnhyim.home.search

import android.util.Log
import android.widget.ToggleButton
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconToggleButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import com.mnhyim.home.components.SearchBar
import com.mnhyim.home.destinations.OpenWebViewDestination
import com.mnhyim.home.news.NewsCardItem
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun SearchScreen(
    navigator: DestinationsNavigator,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val searchQuery by viewModel.search.collectAsState()
    val searchMode by viewModel.searchMode.collectAsState()

    val news = viewModel.searchNews(searchQuery).collectAsLazyPagingItems()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Text(
            text = "Search global news",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(16.dp)
        )
        SearchBar(
            text = searchQuery,
            checked = searchMode,
            onValueChange = viewModel::changeSearchQuery,
            onChecked = viewModel::changeSearchMode
        )

        if (searchQuery.isEmpty()) {

        } else {
            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Fixed(2),
                verticalItemSpacing = 8.dp,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .padding(0.dp, 8.dp)
                    .fillMaxSize()
            ) {
                items(
                    count = news.itemCount,
                    contentType = news.itemContentType { "Articles" }
                ) { index ->
                    news[index]?.let { article ->
                        NewsCardItem(
                            item = article,
                            onClick = {
                                navigator.navigate(OpenWebViewDestination(it))
                            }
                        )
                    }
                }

                when (val state = news.loadState.refresh) {
                    is LoadState.Error -> {
                        Log.d("Paging", "E: ${state.error}")
                    }

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

                    is LoadState.NotLoading -> {
                        item {
                            Text(text = "Not found!")
                        }
                    }


                    else -> {}
                }

                when (val state = news.loadState.append) {
                    is LoadState.Error -> {
                        Log.d("Paging", "E: ${state.error}")
                    }

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
}