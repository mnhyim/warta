package com.mnhyim.favorite

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.itemContentType
import com.mnhyim.favorite.destinations.OpenWebViewDestination
import com.mnhyim.ui.components.NewsCardItem
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.flow.collectLatest

@RootNavGraph(start = true)
@Destination
@Composable
fun FavoriteScreen(
    navigator: DestinationsNavigator,
    viewModel: FavoriteViewModel = hiltViewModel()
) {

    val favorites by viewModel.favorites.collectAsState()

    Column {
        Text(
            text = "Favorite",
            textAlign = TextAlign.Center,
            fontFamily = FontFamily.Serif,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(16.dp)
        )

        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            verticalItemSpacing = 8.dp,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .padding(8.dp)
                .fillMaxSize()
        ) {
            items(items = favorites) { article ->
                NewsCardItem(
                    articleUrl = article.url,
                    articleTitle = article.title,
                    articlePublishedAt = article.publishedAt,
                    articleDescription = article.description,
                    onClick = {
                        navigator.navigate(OpenWebViewDestination(it))
                    },
                    onSave = {  }
                )
            }

        }
    }
}