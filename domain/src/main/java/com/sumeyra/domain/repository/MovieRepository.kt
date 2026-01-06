package com.sumeyra.domain.repository

import com.sumeyra.domain.model.MovieDetailedModel
import com.sumeyra.domain.model.MovieListModel

/**
 * Created on 6.01.2026
 * @author Sümeyra Özuğur
 */

interface MovieRepository {

    suspend fun getNowPlayingMovies(page: Int): MovieListModel

    suspend fun getUpcomingMovies(page: Int): MovieListModel

    suspend fun getMovieDetail(movieId: Int): MovieDetailedModel
}
