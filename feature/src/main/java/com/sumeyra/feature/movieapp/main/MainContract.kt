package com.sumeyra.feature.movieapp.main

import android.graphics.Movie
import com.sumeyra.domain.model.MovieModel
import com.sumeyra.feature.movieapp.base.UiEffect
import com.sumeyra.feature.movieapp.base.UiEvent
import com.sumeyra.feature.movieapp.base.UiState
import javax.annotation.concurrent.Immutable

class MainContract {
    @Immutable
    data class State(
        val isRefreshing: Boolean = false,
        val isLoadingMore: Boolean = false,
        val page: Int = 1,
        val pageCount: Int? = null,
        val upcomingMovieList: List<MovieModel> = emptyList(),
        val nowPlayingMovieList: List<MovieModel> = emptyList(),
        val sliderMovies: List<MovieModel> = emptyList(),
    ) : UiState {
        val canLoadMore: Boolean = pageCount?.let { page < it } ?: false
    }

    sealed class Event : UiEvent {
        data class OnMovieClicked(val id: String) : Event()
        object OnRefresh : Event()
        object OnLoadPage : Event()
    }

    sealed class Effect : UiEffect {
        data class NavigateToDetail(val id: String) : Effect()
    }
}