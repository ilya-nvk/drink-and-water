package com.ilyanvk.drinkwater.data.datasource.userprofile

import android.content.Context
import com.ilyanvk.drinkwater.domain.model.UserProfile

interface UserProfileSharedPreferences {
    fun saveUserProfile(context: Context, userProfile: UserProfile)
    fun getUserProfile(context: Context): UserProfile
}