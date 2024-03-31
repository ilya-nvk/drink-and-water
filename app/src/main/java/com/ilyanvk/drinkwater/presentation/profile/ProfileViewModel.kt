package com.ilyanvk.drinkwater.presentation.profile

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ilyanvk.drinkwater.domain.model.UserProfile
import com.ilyanvk.drinkwater.domain.repository.intakerecord.IntakeRecordRepository
import com.ilyanvk.drinkwater.domain.repository.notifications.NotificationsRepository
import com.ilyanvk.drinkwater.domain.repository.plants.GalleryRepository
import com.ilyanvk.drinkwater.domain.repository.userprofile.UserProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userProfileRepository: UserProfileRepository,
    private val intakeRecordRepository: IntakeRecordRepository,
    private val galleryRepository: GalleryRepository,
    private val notificationRepository: NotificationsRepository
) : ViewModel() {
    private val _state = mutableStateOf(
        userProfileToState(userProfileRepository.getUserProfile())
    )
    val state: State<ProfileScreenState> = _state

    init {
        Log.d(TAG, "init")
    }

    fun onEvent(event: ProfileScreenEvent) {
        when (event) {
            is ProfileScreenEvent.UpdateDateOfBirth -> {
                _state.value = _state.value.copy(dateOfBirth = event.dateOfBirth)
            }

            is ProfileScreenEvent.UpdateHeight -> {
                _state.value = _state.value.copy(height = event.height)
            }

            is ProfileScreenEvent.UpdateName -> {
                _state.value = _state.value.copy(name = event.name)
            }

            is ProfileScreenEvent.UpdateSex -> {
                _state.value = _state.value.copy(sex = event.sex)
            }

            is ProfileScreenEvent.UpdateWeight -> {
                _state.value = _state.value.copy(weight = event.weight)
            }

            is ProfileScreenEvent.UpdateActivityLevel -> {
                _state.value = _state.value.copy(activityLevel = event.activityLevel)
            }

            ProfileScreenEvent.ResetProgress -> {
                viewModelScope.launch(Dispatchers.IO) {
                    intakeRecordRepository.clear()
                    galleryRepository.clear()
                    notificationRepository.clear()
                }
            }

            ProfileScreenEvent.SaveProfile -> {
                userProfileRepository.saveUserProfile(stateToUserProfile())
                _state.value = userProfileToState(userProfileRepository.getUserProfile())
            }

            ProfileScreenEvent.ShowDatePickerDialog -> {
                _state.value = _state.value.copy(showDatePickerDialog = true)
            }

            ProfileScreenEvent.HideDatePickerDialog -> {
                _state.value = _state.value.copy(showDatePickerDialog = false)
            }
        }
    }

    @Throws(IllegalArgumentException::class)
    private fun stateToUserProfile(): UserProfile {
        require(_state.value.isNameCorrect() && _state.value.isDateOfBirthCorrect() && _state.value.isWeightCorrect() && _state.value.isHeightCorrect())
        return UserProfile(
            _state.value.name,
            _state.value.dateOfBirth,
            _state.value.height.toInt(),
            _state.value.weight.toDouble(),
            _state.value.sex,
            _state.value.activityLevel
        )
    }

    private fun userProfileToState(userProfile: UserProfile): ProfileScreenState {
        return ProfileScreenState(
            userProfile.name,
            userProfile.dateOfBirth,
            userProfile.height.toString(),
            userProfile.weight.toString(),
            userProfile.sex,
            userProfile.activityLevel
        )
    }


    private companion object {
        private const val TAG = "ProfileViewModel"
    }
}
