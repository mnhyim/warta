package com.mnhyim.warta.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Newspaper
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.mnhyim.favorite.destinations.FavoriteScreenDestination
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
    FAVORITE(
        direction = FavoriteScreenDestination,
        icon = Icons.Default.Favorite,
        label = "Favorite",
    ),

    /* TODO: Add later */
//    ABOUT(
//        direction = CategoriesScreenDestination,
//        icon = Icons.Default.Category,
//        label = "Categories",
//    )
}