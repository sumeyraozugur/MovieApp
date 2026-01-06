package com.sumeyra.domain.model

data class MovieModel(
    val id: Int?,
    val title: String,
    val titleWithDate: String,
    val backdropPath: String,
    val releaseDate: String,
    val overview: String
)