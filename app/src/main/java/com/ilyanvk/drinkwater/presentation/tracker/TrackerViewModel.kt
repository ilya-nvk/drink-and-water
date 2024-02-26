package com.ilyanvk.drinkwater.presentation.tracker

import androidx.compose.runtime.IntState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.ilyanvk.drinkwater.domain.model.DrinkType
import com.ilyanvk.drinkwater.domain.model.IntakeRecord
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class TrackerViewModel @Inject constructor() : ViewModel() {

    private val _intakeToday = mutableIntStateOf(500)
    val intakeToday: IntState = _intakeToday

    private val _intakeGoal = mutableIntStateOf(2000)
    val intakeTodayGoal: IntState = _intakeGoal

    private val _intakeGrow = mutableIntStateOf(500)
    val intakeGrow: IntState = _intakeGrow

    private val _intakeGrowGoal = mutableIntStateOf(2000)
    val intakeGrowGoal: IntState = _intakeGrowGoal

    private val _records = mutableStateOf<List<IntakeRecord>>(
        List(10) {
            IntakeRecord(
                intakeMilliliters = Random.nextInt(
                    100,
                    1000
                ), // Random intake between 100 to 1000 ml
                time = System.currentTimeMillis() - (it * 15 * 60 * 1000), // Time in milliseconds, decreasing by 15 minutes for each record
                drinkType = DrinkType.entries[Random.nextInt(
                    0,
                    DrinkType.entries.size
                )] // Random drink type
            )
        }
    )
    val records: State<List<IntakeRecord>> = _records

    fun newRecord(milliliters: Int, drinkType: DrinkType) {

    }
}