package com.mnhyim.home.news

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph

@RootNavGraph(start = true)
@Destination
@Composable
fun NewsScreen(
    viewModel: NewsViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    Text(text = "NewsScreen")
    Button(onClick = { viewModel.getTopHeadlines() }) {
        Text(text = "Click Me")
    }

    if (state.articles.isNotEmpty()) {
        LazyColumn {
            items(state.articles) { article ->
                Text(text = "${article.author} - ${ article.title}")
            }
        }
    }
}