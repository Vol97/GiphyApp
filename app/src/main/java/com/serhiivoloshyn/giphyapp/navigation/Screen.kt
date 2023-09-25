package com.serhiivoloshyn.giphyapp.navigation

sealed class Screen(val route: String) {
    object Search: Screen(route = "search_screen")
    object Gif: Screen(route = "gif_screen")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}