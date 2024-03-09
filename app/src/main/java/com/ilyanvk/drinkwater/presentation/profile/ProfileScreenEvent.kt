package com.ilyanvk.drinkwater.presentation.profile

import com.ilyanvk.drinkwater.domain.model.Sex

sealed class ProfileScreenEvent {
    data class UpdateName(val name: String) : ProfileScreenEvent()
    data class UpdateDateOfBirth(val dateOfBirth: Long) : ProfileScreenEvent()
    data class UpdateHeight(val height: String) : ProfileScreenEvent()
    data class UpdateWeight(val weight: String) : ProfileScreenEvent()
    data class UpdateSex(val sex: Sex) : ProfileScreenEvent()
    data object SaveProfile : ProfileScreenEvent()
    data object ResetProgress : ProfileScreenEvent()
    data object ShowDatePickerDialog : ProfileScreenEvent()
    data object HideDatePickerDialog : ProfileScreenEvent()
}