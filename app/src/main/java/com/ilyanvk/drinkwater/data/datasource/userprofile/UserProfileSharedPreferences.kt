package com.ilyanvk.drinkwater.data.datasource.userprofile

import com.ilyanvk.drinkwater.domain.model.UserProfile

interface UserProfileSharedPreferences {
    fun saveUserProfile(userProfile: UserProfile)
    fun getUserProfile(): UserProfile
}