package com.sumeyra.data.mapper

import com.sumeyra.data.model.MovieDetailResponse
import com.sumeyra.data.util.buildImageUrl
import com.sumeyra.data.util.getMovieTitleWithDate
import com.sumeyra.domain.model.MovieDetailedModel

/**
 * Created on 6.01.2026
 * @author Sümeyra Özuğur
 */

internal fun MovieDetailResponse.toDomainModel(): MovieDetailedModel {

    return MovieDetailedModel(
        id = id,
        title = title.orEmpty(),
        titleWithDate = getMovieTitleWithDate(title.orEmpty(), releaseDate.orEmpty()),
        backdropPath = buildImageUrl(backdropPath),
        releaseDate = releaseDate.orEmpty(),
        overview = overview.orEmpty(),
        rating = voteAverage
    )
}
