package com.ilyanvk.drinkwater.domain.model

import androidx.room.TypeConverter

class DrinkTypeConverter {
    @TypeConverter
    fun fromDrinkType(drinkType: DrinkType): Int = drinkType.ordinal

    @TypeConverter
    fun toDrinkType(ordinal: Int): DrinkType = DrinkType.entries[ordinal]
}