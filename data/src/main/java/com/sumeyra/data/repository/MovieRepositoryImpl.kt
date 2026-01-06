package com.sumeyra.data.repository

import com.sumeyra.data.mapper.toDomainModel
import com.sumeyra.data.sevice.MovieService
import com.sumeyra.domain.model.MovieDetailedModel
import com.sumeyra.domain.model.MovieListModel
import com.sumeyra.domain.repository.MovieRepository
import javax.inject.Inject

/**
 * Created on 6.01.2026
 * @author Sümeyra Özuğur
 */

internal class MovieRepositoryImpl @Inject constructor(
    private val movieService: MovieService
) : MovieRepository {

    override suspend fun getNowPlayingMovies(page: Int): MovieListModel {
        val response = movieService.getNowPlaying(page)
        return response.toDomainModel()
    }

    override suspend fun getUpcomingMovies(page: Int): MovieListModel {
        val response = movieService.getUpcoming(page)
        return response.toDomainModel()
    }

    override suspend fun getMovieDetail(movieId: Int): MovieDetailedModel {
        val response = movieService.getMovieDetail(movieId)
        return response.toDomainModel()
    }
}