package com.sumeyra.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Created on 4.01.2026
 * @author Sümeyra Özuğur
 */
@Serializable
data class MovieListResponseDto(
    @SerialName("dates") val dates: DatesDto ?= null,
    @SerialName("page") val page: Int ?= null,
    @SerialName("results") val results: List<MovieDto?> ?= null,
    @SerialName("total_pages")  val totalPages: Int ?= null,
    @SerialName("total_results")val totalResults: Int ?= null,
)