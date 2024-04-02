package com.ilyanvk.drinkwater.presentation.settings

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ilyanvk.drinkwater.domain.model.Notification
import com.ilyanvk.drinkwater.domain.model.Theme
import com.ilyanvk.drinkwater.domain.repository.notifications.NotificationsRepository
import com.ilyanvk.drinkwater.domain.repository.settings.ThemeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val themeRepository: ThemeRepository,
    private val notificationsRepository: NotificationsRepository,
) : ViewModel() {
    private val _state =
        mutableStateOf(SettingsScreenState(themeRepository.getTheme().value ?: Theme.SYSTEM))
    val state: State<SettingsScreenState> = _state

    init {
        Log.d(TAG, "init")
        notificationsRepository.getNotifications().onEach { notifications ->
            _state.value = _state.value.copy(notifications = notifications)
        }.launchIn(viewModelScope)
    }

    fun onEvent(event: SettingsEvent) {
        when (event) {
            is SettingsEvent.AddNotification -> {
                val notification = Notification(time = event.time)
                viewModelScope.launch(Dispatchers.IO) {
                    notificationsRepository.addNotification(notification)
                }
            }

            is SettingsEvent.RemoveNotification -> {
                viewModelScope.launch(Dispatchers.IO) {
                    notificationsRepository.deleteNotification(event.notification)
                }
            }

            is SettingsEvent.SetTheme -> {
                Log.d("SettingsViewModel", "SettingsEvent.SetTheme")
                themeRepository.setTheme(event.theme)
                _state.value = _state.value.copy(theme = event.theme)
            }

            is SettingsEvent.UpdateNotification -> {
                viewModelScope.launch(Dispatchers.IO) {
                    notificationsRepository.updateNotification(event.notification)
                }
            }

            SettingsEvent.ShowTimePickerDialog -> {
                _state.value = _state.value.copy(showTimePickerDialog = true)
            }

            SettingsEvent.HideTimePickerDialog -> {
                _state.value = _state.value.copy(showTimePickerDialog = false)
            }

        }
    }


    private companion object {
        private const val TAG = "SettingsViewModel"
    }
}
