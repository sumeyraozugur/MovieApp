package com.sumeyra.feature.movieapp.detail

import com.sumeyra.domain.model.MovieDetailedModel
import com.sumeyra.feature.movieapp.base.UiEffect
import com.sumeyra.feature.movieapp.base.UiEvent
import com.sumeyra.feature.movieapp.base.UiState
import javax.annotation.concurrent.Immutable

class DetailContract {

    @Immutable
    data class State(
        val isLoading: Boolean = false,
        val movie: MovieDetailedModel? = null,
        val error: String? = null
    ) : UiState

    sealed class Event : UiEvent {
        data class Load(val movieId: Int) : Event()
        object OnBackClicked : Event()
        object OnRetry : Event()
        object OnDiscoverPopularClicked : Event()
    }

    sealed class Effect : UiEffect {
        object NavigateBack : Effect()
        data class ShowMessage(val message: String) : Effect()
    }
}