package com.ilyanvk.drinkwater.presentation.settings

import com.ilyanvk.drinkwater.domain.model.Notification
import com.ilyanvk.drinkwater.domain.model.Theme

sealed class SettingsEvent {
    data class SetTheme(val theme: Theme) : SettingsEvent()
    data class AddNotification(val time: Long) : SettingsEvent()
    data class RemoveNotification(val notification: Notification) : SettingsEvent()
    data class UpdateNotification(val notification: Notification) : SettingsEvent()
}
