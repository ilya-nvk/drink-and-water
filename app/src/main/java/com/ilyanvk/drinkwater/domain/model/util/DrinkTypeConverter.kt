package com.ilyanvk.drinkwater.domain.model.util

import androidx.room.TypeConverter
import com.ilyanvk.drinkwater.domain.model.DrinkType

class DrinkTypeConverter {
    @TypeConverter
    fun fromDrinkType(drinkType: DrinkType): Int = drinkType.ordinal

    @TypeConverter
    fun toDrinkType(ordinal: Int): DrinkType = DrinkType.entries[ordinal]
}