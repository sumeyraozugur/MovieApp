package com.sumeyra.movieapp.ui.navigation


import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.sumeyra.core.ui.base.Screen
import com.sumeyra.feature.movieapp.detail.DetailScreen
import com.sumeyra.feature.movieapp.detail.DetailViewModel
import com.sumeyra.feature.movieapp.main.MainScreen
import com.sumeyra.feature.movieapp.main.MainViewModel


@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.MainScreen.route
    ) {
        composable(route = Screen.MainScreen.route) {
            val viewModel: MainViewModel = hiltViewModel()
            val state by viewModel.uiState.collectAsState()
            val effect by viewModel.effect.collectAsState(initial = null)
            MainScreen(
                onMovieClick = { id ->
                    navController.navigate(Screen.DetailScreen.createRoute(id))
                },
                state = state,
                effect = effect,
                event = viewModel::handleEvent)
        }

        composable(
            route = Screen.DetailScreen.route,
            arguments = listOf(navArgument("movieId") { type = NavType.IntType })
        ) { backStackEntry ->
            val viewModel: DetailViewModel = hiltViewModel()
            val state by viewModel.uiState.collectAsState()
            val effect by viewModel.effect.collectAsState(initial = null)
            DetailScreen(
                onBack = { navController.popBackStack() },
                state = state,
                event = viewModel::handleEvent,
                effect = effect
            )
        }

    }
}
