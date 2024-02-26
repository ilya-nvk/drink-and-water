package com.ilyanvk.drinkwater.domain.repository.userprofile

import com.ilyanvk.drinkwater.domain.model.Sex
import com.ilyanvk.drinkwater.domain.model.UserProfile

interface UserProfileRepository {
    fun saveUserProfile(
        name: String = getUserProfile().name,
        dateOfBirth: String = getUserProfile().dateOfBirth,
        height: Double = getUserProfile().height,
        weight: Double = getUserProfile().weight,
        sex: Sex = getUserProfile().sex
    )

    fun getUserProfile(): UserProfile
}