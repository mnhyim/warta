package com.mnhyim.home.sources

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import com.mnhyim.home.destinations.NewsScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun SourcesScreen(
    category: String,
    navigator: DestinationsNavigator,
    viewModel: SourcesViewModel = hiltViewModel()
) {

    val sources = viewModel.getSources(category).collectAsLazyPagingItems()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()
    ) {

        Text(
            text = "$category",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineMedium
        )

        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            verticalItemSpacing = 8.dp,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .padding(0.dp, 8.dp)
                .fillMaxSize()
        ) {
            items(
                count = sources.itemCount,
                contentType = sources.itemContentType { "Articles" }
            ) { index ->
                sources[index]?.let { source ->
                    SourcesCardItem(
                        item = source,
                        onClick = { id, name, description ->
                            navigator.navigate(NewsScreenDestination(id, name, description))
                        }
                    )
                }
            }

            when (val state = sources.loadState.refresh) {
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

            when (val state = sources.loadState.append) {
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