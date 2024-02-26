package com.ilyanvk.drinkwater.domain.repository.userprofile

import android.content.Context
import com.ilyanvk.drinkwater.data.datasource.userprofile.UserProfileSharedPreferences
import com.ilyanvk.drinkwater.domain.model.Sex
import com.ilyanvk.drinkwater.domain.model.UserProfile
import com.ilyanvk.drinkwater.domain.util.InvalidUserProfileException

class UserProfileRepositoryImpl(
    private val userProfileSharedPreferences: UserProfileSharedPreferences,
    private val context: Context
) : UserProfileRepository {
    override fun saveUserProfile(
        name: String, dateOfBirth: String, height: Double, weight: Double, sex: Sex
    ) {
        val birthPattern = Regex("^(0[1-9]|1[0-2])/(0[1-9]|[12][0-9]|3[01])/((19|20)\\d\\d)\$")
        if (name == "" || !birthPattern.matches(dateOfBirth) || height <= 0.0 || weight <= 0.0) {
            throw InvalidUserProfileException("Invalid date of birth")
        }
        userProfileSharedPreferences.saveUserProfile(
            context, UserProfile(name, dateOfBirth, height, weight, sex)
        )
    }

    override fun getUserProfile(): UserProfile {
        return userProfileSharedPreferences.getUserProfile(context)
    }
}