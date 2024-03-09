package com.ilyanvk.drinkwater.data.datasource.userprofile

import android.content.Context
import com.ilyanvk.drinkwater.domain.model.Sex
import com.ilyanvk.drinkwater.domain.model.UserProfile

class UserProfileSharedPreferencesImpl(
    private val context: Context
) : UserProfileSharedPreferences {
    override fun saveUserProfile(userProfile: UserProfile) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        with(prefs.edit()) {
            putString(KEY_NAME, userProfile.name)
            putLong(KEY_DOB, userProfile.dateOfBirth)
            putInt(KEY_HEIGHT, userProfile.height)
            putFloat(KEY_WEIGHT, userProfile.weight.toFloat())
            putInt(KEY_SEX, userProfile.sex.ordinal)
            apply()
        }
    }

    override fun getUserProfile(): UserProfile {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return UserProfile(
            prefs.getString(KEY_NAME, "") ?: "",
            prefs.getLong(KEY_DOB, 0),
            prefs.getInt(KEY_HEIGHT, 0),
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