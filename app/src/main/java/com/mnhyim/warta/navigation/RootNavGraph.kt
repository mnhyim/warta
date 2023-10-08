package com.mnhyim.warta.navigation

import com.mnhyim.home.HomeNavGraph
import com.ramcosta.composedestinations.spec.DestinationSpec
import com.ramcosta.composedestinations.spec.NavGraphSpec
import com.ramcosta.composedestinations.spec.Route

object RootNavGraph: NavGraphSpec {

    override val route = "root"

    override val startRoute: Route = HomeNavGraph

    override val destinationsByRoute = emptyMap<String, DestinationSpec<*>>()

    override val nestedNavGraphs: List<NavGraphSpec> = listOf(
        HomeNavGraph
    )
}