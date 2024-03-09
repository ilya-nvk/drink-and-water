package com.ilyanvk.drinkwater.domain.repository.userprofile

import com.ilyanvk.drinkwater.data.datasource.userprofile.UserProfileSharedPreferences
import com.ilyanvk.drinkwater.domain.model.UserProfile

class UserProfileRepositoryImpl(
    private val userProfileSharedPreferences: UserProfileSharedPreferences
) : UserProfileRepository {
    override fun saveUserProfile(userProfile: UserProfile) {
        userProfileSharedPreferences.saveUserProfile(userProfile)
    }

    override fun getUserProfile(): UserProfile {
        return userProfileSharedPreferences.getUserProfile()
    }
}