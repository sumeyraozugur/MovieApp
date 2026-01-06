package com.sumeyra.data.mapper

import com.sumeyra.data.model.MovieDto
import com.sumeyra.data.util.buildImageUrl
import com.sumeyra.data.util.getMovieTitleWithDate
import com.sumeyra.domain.model.MovieModel

/**
 * Created on 6.01.2026
 * @author Sümeyra Özuğur
 */

internal fun MovieDto.toDomainModel(): MovieModel {

    return MovieModel(
        id = id,
        title = title.orEmpty(),
        titleWithDate = getMovieTitleWithDate(title.orEmpty(), releaseDate.orEmpty()),
        backdropPath = buildImageUrl(backdropPath),
        releaseDate = releaseDate.orEmpty(),
        overview = overview.orEmpty()
    )
}