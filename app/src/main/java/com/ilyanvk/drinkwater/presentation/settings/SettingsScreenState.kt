package com.ilyanvk.drinkwater.presentation.settings

import com.ilyanvk.drinkwater.domain.model.Notification
import com.ilyanvk.drinkwater.domain.model.Theme

data class SettingsScreenState(
    val theme: Theme = Theme.SYSTEM,
    val notifications: List<Notification> = emptyList(),
    val showTimePickerDialog: Boolean = false
)
