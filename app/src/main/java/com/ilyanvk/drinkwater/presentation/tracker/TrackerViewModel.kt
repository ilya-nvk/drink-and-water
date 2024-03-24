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
    private val coinsRepository: CoinsRepository
) : ViewModel() {

    private val _state = mutableStateOf(TrackerScreenState())
    val state: State<TrackerScreenState> = _state

    init {
        intakeRecordRepository.getIntakeRecords().onEach {
            _state.value = _state.value.copy(records = it)
        }.launchIn(viewModelScope)
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
                _state.value = _state.value.copy(
                    records = _state.value.records + newRecord
                )
                viewModelScope.launch(Dispatchers.IO) {
                    var currentPlant = galleryRepository.getCurrentPlant()
                    currentPlant?.let { galleryRepository.updateCurrentPlant(it.copy(tookWater = it.tookWater + newRecord.actualIntakeMilliliters)) }
                    currentPlant = galleryRepository.getCurrentPlant()
                    if (_state.value.intakeToday >= _state.value.intakeTodayGoal) {
                        coinsRepository.addCoins(1)
                    }
                    if (currentPlant?.level == 3) {
                        coinsRepository.addCoins(5)
                        galleryRepository.deleteCurrentPlant()
                    }
                    updateGrowingPlant()
                }
            }

            is TrackerScreenEvent.DeleteRecord -> {
                _state.value = _state.value.copy(
                    records = _state.value.records - event.record
                )
            }
        }
    }

    private fun calculateDailyIntakeGoal(userProfile: UserProfile): Int {
        return 1500
    }
}
