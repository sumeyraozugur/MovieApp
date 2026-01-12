package com.sumeyra.feature

import com.sumeyra.domain.model.MovieListModel
import com.sumeyra.domain.model.MovieModel
import com.sumeyra.domain.model.MovieDetailedModel
import org.mockito.Mockito.mock

object TestDataFactory {

    fun createMovieModel() = mock(MovieModel::class.java)

    fun createMovieListModel(
        page: Int = 1,
        totalPage: Int = 5
    ) = MovieListModel(
        movieList = listOf(createMovieModel(), createMovieModel()),
        page = page,
        totalPage = totalPage
    )

    fun createMovieDetailedModel(
        id: Int = 101,
        title: String = "Inception"
    ) = MovieDetailedModel(
        id = id,
        title = title,
        titleWithDate = "$title (2010)",
        backdropPath = "/path.jpg",
        releaseDate = "2010",
        overview = "Great movie",
        rating = 8.8
    )
}