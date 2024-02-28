package com.ilyanvk.drinkwater.domain.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.ilyanvk.drinkwater.domain.model.util.DrinkTypeConverter
import java.util.UUID

@Entity
data class IntakeRecord(
    val intakeMilliliters: Int,
    val time: Long,
    @TypeConverters(DrinkTypeConverter::class) val drinkType: DrinkType,
    @PrimaryKey val id: String = UUID.randomUUID().toString()
) {
    @Ignore
    val actualIntakeMilliliters: Int = (intakeMilliliters * drinkType.coefficient).toInt()
}