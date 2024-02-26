package com.ilyanvk.drinkwater.data.datasource.userprofile

import android.content.Context
import com.ilyanvk.drinkwater.domain.model.Sex
import com.ilyanvk.drinkwater.domain.model.UserProfile

class UserProfileSharedPreferencesImpl : UserProfileSharedPreferences {
    override fun saveUserProfile(context: Context, userProfile: UserProfile) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        with(prefs.edit()) {
            putString(KEY_NAME, userProfile.name)
            putString(KEY_DOB, userProfile.dateOfBirth)
            putFloat(KEY_HEIGHT, userProfile.height.toFloat())
            putFloat(KEY_WEIGHT, userProfile.weight.toFloat())
            putInt(KEY_SEX, userProfile.sex.ordinal)
            apply()
        }
    }

    override fun getUserProfile(context: Context): UserProfile {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return UserProfile(
            prefs.getString(KEY_NAME, "") ?: "",
            prefs.getString(KEY_DOB, "") ?: "",
            prefs.getFloat(KEY_HEIGHT, 0f).toDouble(),
            prefs.getFloat(KEY_WEIGHT, 0f).toDouble(),
            Sex.entries[prefs.getInt(KEY_SEX, 0)]
        )
    }

    companion object {
        private const val PREF_NAME = "UserPrefs"
        private const val KEY_NAME = "name"
        private const val KEY_DOB = "date_of_birth"
        private const val KEY_HEIGHT = "height"
        private const val KEY_WEIGHT = "weight"
        private const val KEY_SEX = "sex"
    }
}