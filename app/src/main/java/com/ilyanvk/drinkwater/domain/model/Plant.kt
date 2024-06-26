package com.ilyanvk.drinkwater.domain.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.util.UUID
import kotlin.math.min

@Entity
data class Plant(
    @StringRes val nameStringId: Int,
    val price: Int,
    val waterPerLevel: Int,
    @DrawableRes val pictureId1: Int,
    @DrawableRes val pictureId2: Int,
    @DrawableRes val pictureId3: Int,
    @DrawableRes val pictureId4: Int,
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val tookWater: Int = 0
) {
    @Ignore
    val totalWater = waterPerLevel * 3

    @Ignore
    val level = min(tookWater / waterPerLevel, 3)

    @DrawableRes
    fun getCurrentLevelPictureId(): Int {
        return when (level) {
            0 -> pictureId1
            1 -> pictureId2
            2 -> pictureId3
            else -> pictureId4
        }
    }
}
