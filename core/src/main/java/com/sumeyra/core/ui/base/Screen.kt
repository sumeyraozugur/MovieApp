package com.sumeyra.core.ui.base

sealed class Screen(val route: String) {
    object MainScreen : Screen("main_screen")

    object DetailScreen : Screen("detail_screen/{movieId}") {
        fun createRoute(movieId: String) = "detail_screen/$movieId"
    }

}