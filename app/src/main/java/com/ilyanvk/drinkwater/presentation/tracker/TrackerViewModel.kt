package com.ilyanvk.drinkwater.presentation.tracker

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ilyanvk.drinkwater.domain.model.IntakeRecord
import com.ilyanvk.drinkwater.domain.model.UserProfile
import com.ilyanvk.drinkwater.domain.repository.coins.CoinsRepository
import com.ilyanvk.drinkwater.domain.repository.intakerecord.IntakeRecordRepository
import com.ilyanvk.drinkwater.domain.repository.lastlogin.LastLoginRepository
import com.ilyanvk.drinkwater.domain.repository.plants.GalleryRepository
import com.ilyanvk.drinkwater.domain.repository.userprofile.UserProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class TrackerViewModel @Inject constructor(
    private val intakeRecordRepository: IntakeRecordRepository,
    private val profileRepository: UserProfileRepository,
    private val galleryRepository: GalleryRepository,
    private val coinsRepository: CoinsRepository,
    lastLoginRepository: LastLoginRepository
) : ViewModel() {

    private val _state = mutableStateOf(TrackerScreenState())
    val state: State<TrackerScreenState> = _state

    init {
        Log.d(TAG, "init")
        intakeRecordRepository.getIntakeRecords().onEach {
            _state.value = _state.value.copy(records = it)
        }.launchIn(viewModelScope)

        if (lastLoginRepository.isFirstLoginToday()) {
            addCoins(1)
        }
    }

    fun updateGrowingPlant() {
        viewModelScope.launch(Dispatchers.IO) {
            updateGrowingPlantState()
        }
    }

    private suspend fun updateGrowingPlantState() {
        val plant = galleryRepository.getCurrentPlant()
        Log.d("TrackerViewModel", "plant: $plant")
        val userProfile = profileRepository.getUserProfile()
        withContext(Dispatchers.Main) {
            _state.value = _state.value.copy(
                plant = plant, intakeTodayGoal = calculateDailyIntakeGoal(userProfile)
            )
        }
    }

    fun onEvent(event: TrackerScreenEvent) {
        when (event) {
            is TrackerScreenEvent.NewRecord -> {
                val newRecord = IntakeRecord(
                    time = System.currentTimeMillis(),
                    intakeMilliliters = event.milliliters,
                    drinkType = event.drinkType
                )
                viewModelScope.launch(Dispatchers.IO) {
                    intakeRecordRepository.addIntakeRecord(newRecord)
                    val oldPlant = galleryRepository.getCurrentPlant()
                    var currentPlant = oldPlant
                    currentPlant?.let { galleryRepository.updateCurrentPlant(it.copy(tookWater = it.tookWater + newRecord.actualIntakeMilliliters)) }
                    currentPlant = galleryRepository.getCurrentPlant()
                    if (_state.value.intakeToday >= _state.value.intakeTodayGoal && currentPlant?.level == 3) {
                        addCoins(6)
                        galleryRepository.deleteCurrentPlant()
                        _state.value = _state.value.copy(showPlantIsGrownDialog = true)
                    } else if (_state.value.intakeToday >= _state.value.intakeTodayGoal) {
                        addCoins(1)
                    } else if (currentPlant?.level == 3) {
                        addCoins(5)
                        galleryRepository.deleteCurrentPlant()
                        _state.value = _state.value.copy(showPlantIsGrownDialog = true)
                    }
                    if ((oldPlant?.level ?: 0) < (currentPlant?.level ?: 0)) {
                        _state.value = _state.value.copy(showNextLevelPlantDialog = true)
                    }
                    updateGrowingPlant()
                }
            }

            is TrackerScreenEvent.DeleteRecord -> {
                _state.value = _state.value.copy(
                    records = _state.value.records - event.record
                )
            }

            TrackerScreenEvent.HideEarnedCoinsDialog -> {
                _state.value = _state.value.copy(coinsEarned = null)
            }

            TrackerScreenEvent.HideNextLevelPlantDialog -> {
                _state.value = _state.value.copy(showNextLevelPlantDialog = false)

            }

            TrackerScreenEvent.HidePlantGrownDialog -> {
                _state.value = _state.value.copy(showPlantIsGrownDialog = false)
            }
        }
    }

    private fun addCoins(coins: Int) {
        coinsRepository.addCoins(coins)
        _state.value = _state.value.copy(coinsEarned = coins)
    }

    private fun calculateDailyIntakeGoal(userProfile: UserProfile): Int {
        return 1500
    }

    private companion object {
        private const val TAG = "TrackerViewModel"
    }
}
