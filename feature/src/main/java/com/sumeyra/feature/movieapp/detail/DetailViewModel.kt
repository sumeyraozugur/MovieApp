package com.sumeyra.feature.movieapp.detail

import androidx.lifecycle.SavedStateHandle
import com.sumeyra.domain.usecase.GetMovieDetailUseCase
import com.sumeyra.feature.movieapp.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created on 9.01.2026
 * @author Sümeyra Özuğur
 */

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getMovieDetailUseCase: GetMovieDetailUseCase,
    savedStateHandle: SavedStateHandle
) : BaseViewModel<DetailContract.State, DetailContract.Event, DetailContract.Effect>() {

    override fun createInitialState() = DetailContract.State()


    private val movieId: Int? = savedStateHandle["movieId"]

    init {
        movieId?.let { load(it) }
    }



    fun load(id: Int) {
        launchSafe(
            loadingState = { copy(isLoading = true, error = null) }
        ) {
            val result = getMovieDetailUseCase(id)
            setState {
                copy(isLoading = false, movie = result)
            }
        }
    }

   override fun handleEvent(event: DetailContract.Event) {
        when (event) {
            is DetailContract.Event.Load -> load(event.movieId)

            DetailContract.Event.OnBackClicked ->
                setEffect { DetailContract.Effect.NavigateBack }

            DetailContract.Event.OnRetry -> {
                val idToLoad = currentState.movie?.id ?: movieId
                idToLoad?.let { load(it) }
            }

            DetailContract.Event.OnDiscoverPopularClicked ->
                setEffect { DetailContract.Effect.ShowMessage("sumeyra") }

        }
    }
}
