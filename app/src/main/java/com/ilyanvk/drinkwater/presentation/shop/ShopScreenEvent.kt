package com.ilyanvk.drinkwater.presentation.shop

sealed class ShopScreenEvent {
    data class BuyPlant(val id: String) : ShopScreenEvent()
}