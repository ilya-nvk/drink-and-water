package com.ilyanvk.drinkwater.presentation.profile

import com.ilyanvk.drinkwater.domain.model.Sex
import com.ilyanvk.drinkwater.domain.model.UserProfile

data class ProfileScreenState(
    val name: String, val dateOfBirth: Long, val height: String, val weight: String, val sex: Sex
) {
    fun isNameCorrect(): Boolean = name.isNotEmpty()

    fun isDateOfBirthCorrect(): Boolean {
        return (System.currentTimeMillis() - dateOfBirth) in (YEAR_IN_MILLISECONDS * 1)..(YEAR_IN_MILLISECONDS * 150)
    }

    fun isHeightCorrect(): Boolean {
        try {
            require(height.toInt() in 50..250)
            return true
        } catch (_: Exception) {
            return false
        }
    }

    fun isWeightCorrect(): Boolean {
        try {
            require(weight.toDouble() in 1.0..300.0)
            return true
        } catch (_: Exception) {
            return false
        }
    }

    @Throws(IllegalArgumentException::class)
    fun toUserProfile(): UserProfile {
        require(isNameCorrect() && isDateOfBirthCorrect() && isWeightCorrect() && isHeightCorrect())
        return UserProfile(name, dateOfBirth, height.toInt(), weight.toDouble(), sex)
    }

    companion object {
        fun fromUserProfile(userProfile: UserProfile): ProfileScreenState {
            return ProfileScreenState(
                userProfile.name,
                userProfile.dateOfBirth,
                userProfile.height.toString(),
                userProfile.weight.toString(),
                userProfile.sex
            )
        }

        private const val YEAR_IN_MILLISECONDS = 31536000000
    }
}