package com.sumeyra.feature.movieapp.main

import com.sumeyra.domain.usecase.GetNowPlayingMoviesUseCase
import com.sumeyra.domain.usecase.GetUpcomingMoviesUseCase
import com.sumeyra.feature.movieapp.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created on 7.01.2026
 * @author Sümeyra Özuğur
 */

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getUpcomingMoviesUseCase: GetUpcomingMoviesUseCase,
    private val getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase
) : BaseViewModel<MainContract.State, MainContract.Event, MainContract.Effect>() {

    override fun createInitialState() = MainContract.State()

    init {
        getNowPlayingMovies()
        getUpComingMovies()
    }


    fun getUpComingMovies(page: Int = 0) {
        launchSafe {
            val upComingMovies = getUpcomingMoviesUseCase(page)
            setState {
                copy(sliderMovies = upComingMovies.movieList.take(5))
            }
        }
    }



    fun getNowPlayingMovies(page: Int = 1, isRefresh: Boolean = false) {
        launchSafe(
            loadingState = {
                when {
                    isRefresh -> copy(isRefreshing = true)
                    page > 1 -> copy(isLoadingMore = true)
                    else -> this
                }
            }

        ) {
            val movieListResponse = getNowPlayingMoviesUseCase(page)

            setState {
                copy(
                    nowPlayingMovieList = if (page == 1) movieListResponse.movieList
                    else nowPlayingMovieList + movieListResponse.movieList,
                    page = page,
                    pageCount = movieListResponse.totalPage,
                    isRefreshing = false,
                    isLoadingMore = false
                )
            }
        }
    }


    override fun handleEvent(event: MainContract.Event) {
        when (event) {
            MainContract.Event.OnLoadPage -> {
                getNowPlayingMovies(currentState.page + 1)
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
