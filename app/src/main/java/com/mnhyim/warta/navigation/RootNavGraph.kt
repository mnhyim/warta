package com.mnhyim.warta.navigation

import com.mnhyim.favorite.FavoriteNavGraph
import com.mnhyim.news.NewsNavGraph
import com.mnhyim.search.SearchNavGraph
import com.ramcosta.composedestinations.spec.DestinationSpec
import com.ramcosta.composedestinations.spec.NavGraphSpec
import com.ramcosta.composedestinations.spec.Route

object RootNavGraph : NavGraphSpec {

    override val route = "root"

    override val startRoute: Route = NewsNavGraph

    override val destinationsByRoute = emptyMap<String, DestinationSpec<*>>()

    override val nestedNavGraphs: List<NavGraphSpec> = listOf(
        NewsNavGraph,
        SearchNavGraph,
        FavoriteNavGraph
    )
}