package com.ilyanvk.drinkwater.domain.repository.userprofile

import android.content.Context
import com.ilyanvk.drinkwater.data.datasource.userprofile.UserProfileSharedPreferences
import com.ilyanvk.drinkwater.domain.model.UserProfile

class UserProfileRepositoryImpl(
    private val userProfileSharedPreferences: UserProfileSharedPreferences,
    private val context: Context
) : UserProfileRepository {
    override fun saveUserProfile(userProfile: UserProfile) {
        userProfileSharedPreferences.saveUserProfile(context, userProfile)
    }

    override fun getUserProfile(): UserProfile {
        return userProfileSharedPreferences.getUserProfile(context)
    }
}