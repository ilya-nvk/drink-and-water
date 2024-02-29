package com.ilyanvk.drinkwater.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Notification(
    @PrimaryKey val id: Int,
    val time: Long,
    val isActive: Boolean
)

