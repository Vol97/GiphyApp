package com.serhiivoloshyn.giphyapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.serhiivoloshyn.giphyapp.ui.screens.gifScreen.GifScreen
import com.serhiivoloshyn.giphyapp.ui.screens.gifSearchScreen.GifSearchScreen

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Search.route
    ) {
        composable(route = Screen.Search.route) {
            GifSearchScreen(navController = navController)
        }
        composable(
            route = Screen.Gif.route + "/{gifUrl}",
            arguments = listOf(
                navArgument("gifUrl") {
                    type = NavType.StringType
                    nullable = true
                }
            )
        ) { entry ->
            GifScreen(
                gifUrl = entry.arguments?.getString("gifUrl")
            )
        }
    }
}