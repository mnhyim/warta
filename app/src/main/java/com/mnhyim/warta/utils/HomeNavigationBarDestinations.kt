package com.mnhyim.warta.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Newspaper
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.mnhyim.home.destinations.CategoriesScreenDestination
import com.mnhyim.home.destinations.NewsScreenDestination
import com.mnhyim.home.destinations.SearchScreenDestination
import com.ramcosta.composedestinations.spec.DirectionDestinationSpec

enum class HomeNavigationBarDestinations(
    val direction: DirectionDestinationSpec,
    val icon: ImageVector,
    val label: String // TODO: Change to String Resources values
) {
    NEWS(
        direction = NewsScreenDestination,
        icon = Icons.Default.Newspaper,
        label = "News",
    ),
    SEARCH(
        direction = SearchScreenDestination,
        icon = Icons.Default.Search,
        label = "Search",
    ),
    CATEGORIES(
        direction = CategoriesScreenDestination,
        icon = Icons.Default.Category,
        label = "Categories",
    )
}