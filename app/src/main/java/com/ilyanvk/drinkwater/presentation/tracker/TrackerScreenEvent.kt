package com.ilyanvk.drinkwater.presentation.tracker

import com.ilyanvk.drinkwater.domain.model.DrinkType
import com.ilyanvk.drinkwater.domain.model.IntakeRecord

sealed class TrackerScreenEvent {
    data class NewRecord(val drinkType: DrinkType, val milliliters: Int) : TrackerScreenEvent()
    data class DeleteRecord(val record: IntakeRecord) : TrackerScreenEvent()
    data object HideEarnedCoinsDialog : TrackerScreenEvent()
    data object HidePlantGrownDialog : TrackerScreenEvent()
    data object HideNextLevelPlantDialog : TrackerScreenEvent()
}
