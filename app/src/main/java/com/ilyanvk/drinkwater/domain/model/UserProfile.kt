package com.ilyanvk.drinkwater.domain.model

import com.ilyanvk.drinkwater.domain.model.util.ActivityLevel

data class UserProfile(
    val name: String,
    val dateOfBirth: Long,
    val height: Int,
    val weight: Double,
    val sex: Sex,
    val activityLevel: ActivityLevel
)
