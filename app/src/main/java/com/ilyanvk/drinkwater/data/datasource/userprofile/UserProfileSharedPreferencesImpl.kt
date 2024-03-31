package com.ilyanvk.drinkwater.data.datasource.userprofile

import android.content.Context
import com.ilyanvk.drinkwater.domain.model.Sex
import com.ilyanvk.drinkwater.domain.model.UserProfile
import com.ilyanvk.drinkwater.domain.model.util.ActivityLevel

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
            putInt(KEY_ACTIVITY, userProfile.activityLevel.ordinal)
            apply()
        }
    }

    override fun getUserProfile(): UserProfile {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return UserProfile(
            prefs.getString(KEY_NAME, "") ?: "",
            prefs.getLong(KEY_DOB, 946719360000),
            prefs.getInt(KEY_HEIGHT, 175),
            prefs.getFloat(KEY_WEIGHT, 55f).toDouble(),
            Sex.entries[prefs.getInt(KEY_SEX, 0)],
            ActivityLevel.entries[prefs.getInt(KEY_ACTIVITY, 1)]
        )
    }

    companion object {
        private const val PREF_NAME = "UserPrefs"
        private const val KEY_NAME = "name"
        private const val KEY_DOB = "date_of_birth"
        private const val KEY_HEIGHT = "height"
        private const val KEY_WEIGHT = "weight"
        private const val KEY_SEX = "sex"
        private const val KEY_ACTIVITY = "activity"
    }
}
