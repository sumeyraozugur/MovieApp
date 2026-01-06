package com.sumeyra.domain.usecase

import com.sumeyra.domain.model.MovieListModel
import com.sumeyra.domain.repository.MovieRepository
import javax.inject.Inject

class GetNowPlayingMoviesUseCase @Inject constructor(
    private val repository: MovieRepository
)  {

    suspend fun invoke(params: Int): MovieListModel {
        return repository.getNowPlayingMovies(params)
    }
}