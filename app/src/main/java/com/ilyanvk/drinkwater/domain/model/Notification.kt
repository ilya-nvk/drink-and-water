package com.ilyanvk.drinkwater.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity
data class Notification(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val time: Long,
    val isActive: Boolean = true
)

