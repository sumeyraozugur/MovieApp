package com.sumeyra.feature.movieapp.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sumeyra.domain.usecase.GetNowPlayingMoviesUseCase
import com.sumeyra.domain.usecase.GetUpcomingMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created on 7.01.2026
 * @author Sümeyra Özuğur
 */

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getUpcomingMoviesUseCase: GetUpcomingMoviesUseCase,
    private val getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(MainContract.State())
    val uiState = _uiState.asStateFlow()

    private val _effect = Channel<MainContract.Effect>()
    val effect = _effect.receiveAsFlow()

    private val current: MainContract.State get() = uiState.value

    init {
        getNowPlayingMovies()
        getUpComingMovies()
    }

    private fun setState(reduce: MainContract.State.() -> MainContract.State) {
        _uiState.value = _uiState.value.reduce()
    }

    private fun setEffect(builder: () -> MainContract.Effect) {
        viewModelScope.launch { _effect.send(builder()) }
    }

    fun getUpComingMovies(page: Int = 1) {
        viewModelScope.launch {
            val movies = getUpcomingMoviesUseCase(page)
            val upComingMovies = movies
            setState {
                copy(upcomingMovieList = upComingMovies.movieList,
                    sliderMovies = movies.movieList.take(5))
            }
        }
    }

    fun getNowPlayingMovies(page: Int = 1, isRefresh: Boolean = false) {
        viewModelScope.launch {
            setState {
                when {
                    isRefresh -> copy(isRefreshing = true)
                    page > 1 -> copy(isLoadingMore = true)
                    else -> this
                }
            }

            val movieList = getNowPlayingMoviesUseCase(page)

            setState {
                copy(
                    nowPlayingMovieList = if (page == 1) movieList.movieList
                    else nowPlayingMovieList + movieList.movieList,
                    page = page,
                    pageCount = movieList.totalPage,
                    isRefreshing = false,
                    isLoadingMore = false
                )
            }
        }
    }

    fun handleEvent(event: MainContract.Event) {
        when (event) {
            MainContract.Event.OnLoadPage -> {
                getNowPlayingMovies(current.page + 1)
            }
            is MainContract.Event.OnMovieClicked -> {
                setEffect { MainContract.Effect.NavigateToDetail(event.id) }
            }
            MainContract.Event.OnRefresh -> {
                getNowPlayingMovies(isRefresh = true)
            }
        }
    }
}
