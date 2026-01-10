package com.sumeyra.domain.usecase

import com.sumeyra.domain.model.MovieDetailedModel
import com.sumeyra.domain.repository.MovieRepository
import javax.inject.Inject

class GetMovieDetailUseCase @Inject constructor(
    private val repository: MovieRepository
) {

    suspend operator fun invoke(params: Int): MovieDetailedModel {
        return repository.getMovieDetail(params)
    }
}