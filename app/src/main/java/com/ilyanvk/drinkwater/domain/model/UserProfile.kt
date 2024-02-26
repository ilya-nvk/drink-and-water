package com.ilyanvk.drinkwater.domain.model

data class UserProfile(
    val name: String,
    val dateOfBirth: String, // MM/DD/YYYY
    val height: Double,
    val weight: Double,
    val sex: Sex
)
