package com.ilyanvk.drinkwater.presentation.profile

import com.ilyanvk.drinkwater.domain.model.Sex

sealed class ProfileScreenEvent {
    data class ChangeName(val name: String) : ProfileScreenEvent()
    data class ChangeDateOfBirth(val dateOfBirth: Long) : ProfileScreenEvent()
    data class ChangeHeight(val height: String) : ProfileScreenEvent()
    data class ChangeWeight(val weight: String) : ProfileScreenEvent()
    data class ChangeSex(val sex: Sex) : ProfileScreenEvent()
}