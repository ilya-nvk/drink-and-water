package com.ilyanvk.drinkwater.presentation.shop

import com.ilyanvk.drinkwater.domain.model.Plant

data class ShopScreenState(
    val coins: Int = 0,
    val plants: List<Plant> = emptyList(),
    val showCoinsDialog: Boolean = false,
    val buyPlantDialog: Plant? = null
)
