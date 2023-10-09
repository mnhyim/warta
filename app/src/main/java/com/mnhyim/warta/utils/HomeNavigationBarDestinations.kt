package com.mnhyim.warta.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Newspaper
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.mnhyim.news.destinations.CategoriesScreenDestination
import com.mnhyim.search.destinations.SearchScreenDestination
import com.ramcosta.composedestinations.spec.DirectionDestinationSpec

enum class HomeNavigationBarDestinations(
    val direction: DirectionDestinationSpec,
    val icon: ImageVector,
    val label: String // TODO: Change to String Resources values
) {
    CATEGORIES(
        direction = CategoriesScreenDestination,
        icon = Icons.Default.Newspaper,
        label = "News",
    ),
    SEARCH(
        direction = SearchScreenDestination,
        icon = Icons.Default.Search,
        label = "Search",
    ),
    /* TODO: Add later */
//    ABOUT(
//        direction = CategoriesScreenDestination,
//        icon = Icons.Default.Category,
//        label = "Categories",
//    )
}