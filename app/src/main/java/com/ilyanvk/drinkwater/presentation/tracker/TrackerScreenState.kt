package com.ilyanvk.drinkwater.presentation.tracker

import com.ilyanvk.drinkwater.domain.model.IntakeRecord
import com.ilyanvk.drinkwater.domain.model.Plant
import java.util.Date

data class TrackerScreenState(
    val plant: Plant? = null,
    val intakeTodayGoal: Int = 0,
    val records: List<IntakeRecord> = emptyList()
) {
    val intakeGrow: Int = plant?.let { it.tookWater % it.waterPerLevel } ?: 0

    val intakeGrowGoal: Int = plant?.waterPerLevel ?: 0

    val intakeToday: Int = records.filter {
        val currentTime = Date().time
        it.time > currentTime - currentTime % (24 * 60 * 60 * 1000)
    }.sumOf { it.intakeMilliliters }
}
