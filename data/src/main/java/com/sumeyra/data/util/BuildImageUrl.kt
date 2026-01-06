package com.sumeyra.data.util

private const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/"

internal fun buildImageUrl(path: String?, size: String = "w500"): String {
    return if (!path.isNullOrBlank()) "$IMAGE_BASE_URL$size$path" else ""
}