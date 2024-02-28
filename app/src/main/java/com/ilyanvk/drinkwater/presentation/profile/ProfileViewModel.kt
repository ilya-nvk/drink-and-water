package com.ilyanvk.drinkwater.presentation.profile

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.ilyanvk.drinkwater.domain.model.Sex
import com.ilyanvk.drinkwater.domain.model.UserProfile
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor() : ViewModel() {
    private val _state = mutableStateOf<ProfileScreenState>(
        ProfileScreenState.fromUserProfile(
            UserProfile(
                "Ilya", 1112558400000, 182, 55.0, Sex.MALE
            )
        )
    )
    val state: State<ProfileScreenState> = _state

    fun onEvent(event: ProfileScreenEvent) {
        when (event) {
            is ProfileScreenEvent.ChangeDateOfBirth -> {
                _state.value = _state.value.copy(dateOfBirth = event.dateOfBirth)
            }

            is ProfileScreenEvent.ChangeHeight -> {
                _state.value = _state.value.copy(height = event.height)
            }

            is ProfileScreenEvent.ChangeName -> {
                _state.value = _state.value.copy(name = event.name)
            }

            is ProfileScreenEvent.ChangeSex -> {
                _state.value = _state.value.copy(sex = event.sex)
            }

            is ProfileScreenEvent.ChangeWeight -> {
                _state.value = _state.value.copy(weight = event.weight)
            }
        }
    }
}