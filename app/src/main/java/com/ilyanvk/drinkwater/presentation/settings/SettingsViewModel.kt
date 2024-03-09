package com.ilyanvk.drinkwater.presentation.settings

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.Observer
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
    private val notificationsRepository: NotificationsRepository
) : ViewModel() {
    private val _state = mutableStateOf(
        SettingsScreenState(theme = themeRepository.getTheme().value ?: Theme.SYSTEM)
    )
    val state: State<SettingsScreenState> = _state

    private val themeObserver = Observer<Theme> { theme ->
        _state.value = _state.value.copy(theme = theme)
    }

    init {
        themeRepository.getTheme().observeForever(themeObserver)
        notificationsRepository.getNotifications().onEach { notifications ->
            _state.value = _state.value.copy(notifications = notifications)
        }.launchIn(viewModelScope)
    }

    fun onEvent(event: SettingsEvent) {
        when (event) {
            is SettingsEvent.AddNotification -> {
                viewModelScope.launch(Dispatchers.IO) {
                    notificationsRepository.addNotification(
                        Notification(
                            time = event.time
                        )
                    )
                }
            }

            is SettingsEvent.RemoveNotification -> {
                viewModelScope.launch(Dispatchers.IO) {
                    notificationsRepository.deleteNotification(
                        event.notification
                    )
                }
            }

            is SettingsEvent.SetTheme -> {
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

    override fun onCleared() {
        themeRepository.getTheme().removeObserver(themeObserver)
    }
}