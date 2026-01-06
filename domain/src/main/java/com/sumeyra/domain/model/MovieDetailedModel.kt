package com.sumeyra.domain.model

data class MovieDetailedModel(
    val id: Int?,
    val title: String,
    val titleWithDate: String,
    val backdropPath: String,
    val releaseDate: String,
    val overview: String,
    val rating: Double?
)