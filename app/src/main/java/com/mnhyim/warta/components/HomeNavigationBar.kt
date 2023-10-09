package com.mnhyim.warta.components

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.mnhyim.news.NewsNavGraph
import com.mnhyim.warta.utils.HomeNavigationBarDestinations
import com.ramcosta.composedestinations.navigation.navigate
import com.ramcosta.composedestinations.utils.currentDestinationAsState

@Composable
fun HomeNavigationBar(
    navController: NavController
) {
    val currentDestination = navController.currentDestinationAsState().value ?: NewsNavGraph.startRoute

    NavigationBar(
        contentColor = MaterialTheme.colorScheme.primary,
        containerColor = MaterialTheme.colorScheme.onPrimary
    ) {
        HomeNavigationBarDestinations.values().forEach { item ->
            NavigationBarItem(
                selected = currentDestination == item.direction,
                onClick = { navController.navigate(item.direction) { launchSingleTop = true } },
                icon = { Icon(imageVector = item.icon, contentDescription = item.label) }
            )
        }
    }
}