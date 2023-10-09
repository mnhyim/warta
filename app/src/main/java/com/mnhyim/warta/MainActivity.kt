package com.mnhyim.warta

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.mnhyim.ui.theme.WartaTheme
import com.mnhyim.warta.components.HomeNavigationBar
import com.mnhyim.warta.navigation.RootNavGraph
import com.ramcosta.composedestinations.DestinationsNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()

            WartaTheme {
                Scaffold(
                    bottomBar = { HomeNavigationBar(navController = navController) },
                    modifier = Modifier.fillMaxSize(),
                ) { paddingValues ->
                    DestinationsNavHost(
                        navController = navController,
                        navGraph = RootNavGraph,
                        modifier = Modifier.padding(paddingValues)
                    )
                }
            }
        }
    }
}
