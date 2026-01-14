package com.sumeyra.data.mapper

import com.sumeyra.data.model.MovieListResponseDto
import com.sumeyra.domain.model.MovieListModel

internal fun MovieListResponseDto.toDomainModel(): MovieListModel {

    val items = (results.orEmpty()).mapNotNull { it?.toDomainModel() }
    return MovieListModel(
        movieList = items,
        page = page,
        totalPage = totalPages
    )
}
