package com.ilyanvk.drinkwater.presentation.profile

import com.ilyanvk.drinkwater.domain.model.Sex
import com.ilyanvk.drinkwater.domain.model.util.ActivityLevel

data class ProfileScreenState(
    val name: String,
    val dateOfBirth: Long,
    val height: String,
    val weight: String,
    val sex: Sex,
    val activityLevel: ActivityLevel = ActivityLevel.MEDIUM,
    val showDatePickerDialog: Boolean = false,
) {
    fun isNameCorrect(): Boolean = name.isNotEmpty()

    fun isDateOfBirthCorrect(): Boolean {
        val yearInMilliseconds = 31536000000
        return (System.currentTimeMillis() - dateOfBirth) in (yearInMilliseconds * 1)..(yearInMilliseconds * 150)
    }

    fun isHeightCorrect(): Boolean {
        return try {
            require(height.toInt() in 50..250)
            true
        } catch (_: Exception) {
            false
        }
    }

    fun isWeightCorrect(): Boolean {
        return try {
            require(weight.toDouble() in 1.0..300.0)
            true
        } catch (_: Exception) {
            false
        }
    }

    fun isProfileCorrect(): Boolean {
        return isNameCorrect() && isDateOfBirthCorrect() && isHeightCorrect() && isWeightCorrect()
    }
}
