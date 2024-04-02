package com.ilyanvk.drinkwater.presentation.util

import com.ilyanvk.drinkwater.R
import com.ilyanvk.drinkwater.domain.model.DrinkType
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

internal fun convertMillisecondsToTimestamp(milliseconds: Long): String {
    val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
    val date = Date(milliseconds)
    return sdf.format(date)
}

internal fun DrinkType.toStringRes(): Int {
    return when (this) {
        DrinkType.WATER -> R.string.water
        DrinkType.TEA -> R.string.tea
        DrinkType.COFFEE -> R.string.coffee
        DrinkType.SODA -> R.string.soda
        DrinkType.JUICE -> R.string.juice
        DrinkType.MILK -> R.string.milk
        DrinkType.ALCOHOL -> R.string.alcohol
    }
}