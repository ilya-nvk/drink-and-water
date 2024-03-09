package com.ilyanvk.drinkwater.data.datasource.coins

interface CoinsSharedPreferences {
    fun setCoins(coins: Int)
    fun getCoins(): Int
}
