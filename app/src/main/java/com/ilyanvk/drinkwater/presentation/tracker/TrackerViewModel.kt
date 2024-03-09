package com.ilyanvk.drinkwater.presentation.tracker

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.ilyanvk.drinkwater.domain.model.IntakeRecord
import com.ilyanvk.drinkwater.domain.repository.intakerecord.IntakeRecordRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TrackerViewModel @Inject constructor(
    private val intakeRecordRepository: IntakeRecordRepository
) : ViewModel() {

    private val _state = mutableStateOf(TrackerScreenState())
    val state: State<TrackerScreenState> = _state

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
            }

            is TrackerScreenEvent.DeleteRecord -> {
                _state.value = _state.value.copy(
                    records = _state.value.records - event.record
                )
            }
        }
    }
}