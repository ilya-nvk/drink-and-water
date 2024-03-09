package com.ilyanvk.drinkwater.presentation.profile

import com.ilyanvk.drinkwater.domain.model.Sex

data class ProfileScreenState(
    val name: String,
    val dateOfBirth: Long,
    val height: String,
    val weight: String,
    val sex: Sex,
    val showDatePickerDialog: Boolean = false
) {
    fun isNameCorrect(): Boolean = name.isNotEmpty()

    fun isDateOfBirthCorrect(): Boolean {
        val yearInMilliseconds = 31536000000
        return (System.currentTimeMillis() - dateOfBirth) in (yearInMilliseconds * 1)..(yearInMilliseconds * 150)
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

    fun isProfileCorrect(): Boolean {
        return isNameCorrect() && isDateOfBirthCorrect() && isHeightCorrect() && isWeightCorrect()
    }
}