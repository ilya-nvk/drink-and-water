package com.ilyanvk.drinkwater.presentation.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

internal fun convertMillisecondsToTimestamp(milliseconds: Long): String {
    val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
    val date = Date(milliseconds)
    return sdf.format(date)
}