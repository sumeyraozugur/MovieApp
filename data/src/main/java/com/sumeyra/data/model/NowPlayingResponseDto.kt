package com.sumeyra.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created on 4.01.2026
 * @author Sümeyra Özuğur
 */

data class NowPlayingResponseDto(
    @SerializedName("dates") val dates: DatesDto ?= null,
    @SerializedName("page") val page: Int ?= null,
    @SerializedName("results") val results: List<MovieDto?> ?= null,
    @SerializedName("total_pages")  val totalPages: Int ?= null,
    @SerializedName("total_results")val totalResults: Int ?= null,
)