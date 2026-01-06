package com.sumeyra.domain.model

data class MovieListModel(
    val movieList: List<MovieModel>,
    val page: Int?,
    val totalPage: Int?
)