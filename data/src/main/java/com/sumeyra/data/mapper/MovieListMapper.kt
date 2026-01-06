package com.sumeyra.data.mapper

import com.sumeyra.data.model.NowPlayingResponseDto
import com.sumeyra.domain.model.MovieListModel

internal fun NowPlayingResponseDto.toDomainModel(): MovieListModel {

    val items = (results.orEmpty()).mapNotNull { it?.toDomainModel() }
    return MovieListModel(
        movieList = items,
        page = page,
        totalPage = totalPages
    )
}
