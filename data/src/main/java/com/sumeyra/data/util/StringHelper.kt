package com.sumeyra.data.util

internal fun getMovieTitleWithDate(title: String, date: String): String {
    val year = getYearFromDate(date)
    val titleWithDate = if (year > 0) {
        "$title ($year)"
    } else {
        title
    }
    return titleWithDate
}