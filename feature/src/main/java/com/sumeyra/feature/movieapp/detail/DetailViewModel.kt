package com.sumeyra.feature.movieapp.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sumeyra.domain.usecase.GetMovieDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created on 9.01.2026
 * @author Sümeyra Özuğur
 */

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getMovieDetailUseCase: GetMovieDetailUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow(DetailContract.State())
    val uiState = _uiState.asStateFlow()

    private val _effect = Channel<DetailContract.Effect>()
    val effect = _effect.receiveAsFlow()

    private val current: DetailContract.State get() = uiState.value

    private val movieId: Int? = savedStateHandle["movieId"]

    init {
        movieId?.let { load(it) }
    }

    private fun setState(reduce: DetailContract.State.() -> DetailContract.State) {
        _uiState.value = _uiState.value.reduce()
    }

    private fun setEffect(builder: () -> DetailContract.Effect) {
        viewModelScope.launch { _effect.send(builder()) }
    }

    fun load(id: Int) {
        viewModelScope.launch {
            setState { copy(isLoading = true, error = null) }
            runCatching {
                getMovieDetailUseCase(id)
            }.onSuccess { movie ->
                setState { copy(isLoading = false, movie = movie) }
            }.onFailure { t ->
                setState { copy(isLoading = false, error = t.message ?: "Bir hata oluştu") }
            }
        }
    }

    fun handleEvent(event: DetailContract.Event) {
        when (event) {
            is DetailContract.Event.Load -> load(event.movieId)

            DetailContract.Event.OnBackClicked ->
                setEffect { DetailContract.Effect.NavigateBack }

            DetailContract.Event.OnRetry -> {
                val idToLoad = current.movie?.id ?: movieId
                idToLoad?.let { load(it) }
            }

            DetailContract.Event.OnDiscoverPopularClicked ->
                setEffect { DetailContract.Effect.ShowMessage("sumeyra") }

            else -> {}
        }
    }
}
