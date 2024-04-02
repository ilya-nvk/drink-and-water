package com.ilyanvk.drinkwater.presentation.shop

import com.ilyanvk.drinkwater.domain.model.Plant

sealed class ShopScreenEvent {
    data class BuyPlant(val plant: Plant) : ShopScreenEvent()
    data object ShowCoinsDialog : ShopScreenEvent()
    data object HideCoinsDialog : ShopScreenEvent()
    data class ShowBuyPlantDialog(val plant: Plant) : ShopScreenEvent()
    data object HideBuyPlantDialog : ShopScreenEvent()
}