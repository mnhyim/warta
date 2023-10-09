package com.mnhyim.home.categories

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.Computer
import androidx.compose.material.icons.filled.HealthAndSafety
import androidx.compose.material.icons.filled.NaturePeople
import androidx.compose.material.icons.filled.PermMedia
import androidx.compose.material.icons.filled.Science
import androidx.compose.material.icons.filled.SportsBaseball
import androidx.compose.ui.graphics.vector.ImageVector

enum class NewsCategories(
    val category: String,
    val icon: ImageVector
) {
    BUSINESS(
        category = "Business",
        icon = Icons.Default.Business
    ),
    ENTERTAINMENT(
        category = "Entertainment",
        icon = Icons.Default.PermMedia
    ),
    GENERAL(
        category = "General",
        icon = Icons.Default.NaturePeople
    ),
    HEALTH(
        category = "Health",
        icon = Icons.Default.HealthAndSafety
    ),
    SCIENCE(
        category = "Science",
        icon = Icons.Default.Science
    ),
    SPORTS(
        category = "Sports",
        icon = Icons.Default.SportsBaseball
    ),
    TECHNOLOGY(
        category = "Technology",
        icon = Icons.Default.Computer
    ),
}