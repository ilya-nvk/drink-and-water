package com.ilyanvk.drinkwater.presentation.settings

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor() : ViewModel() {
    private val _state = mutableStateOf(SettingsScreenState())
    val state: State<SettingsScreenState> = _state

    fun onEvent(event: SettingsEvent) {
        when (event) {
            is SettingsEvent.AddNotification -> {

            }

            is SettingsEvent.RemoveNotification -> {

            }

            is SettingsEvent.SetTheme -> {
                _state.value = _state.value.copy(theme = event.theme)
            }

            is SettingsEvent.UpdateNotification -> {

            }
        }
    }
}