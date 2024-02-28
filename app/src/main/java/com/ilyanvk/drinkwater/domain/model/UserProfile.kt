package com.ilyanvk.drinkwater.domain.model

data class UserProfile(
    val name: String,
    val dateOfBirth: Long,
    val height: Int,
    val weight: Double,
    val sex: Sex
)
