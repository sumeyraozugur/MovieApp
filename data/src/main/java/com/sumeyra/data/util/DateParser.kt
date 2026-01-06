package com.sumeyra.data.util

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

internal fun getYearFromDate(dateString: String): Int {
    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val date: Date = sdf.parse(dateString) ?: return -1
    val calendar = Calendar.getInstance()
    calendar.time = date
    return calendar.get(Calendar.YEAR)
}
