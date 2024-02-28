package com.ilyanvk.drinkwater.domain.repository.userprofile

import com.ilyanvk.drinkwater.domain.model.UserProfile

interface UserProfileRepository {
    fun saveUserProfile(userProfile: UserProfile)
    fun getUserProfile(): UserProfile
}