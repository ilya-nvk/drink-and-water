package com.ilyanvk.drinkwater.domain.repository.coins

import com.ilyanvk.drinkwater.data.datasource.coins.CoinsSharedPreferences

class CoinsRepositoryImpl(
    private val coinsSharedPreferences: CoinsSharedPreferences
) : CoinsRepository {
    override fun getCoins(): Int = coinsSharedPreferences.getCoins()

    override fun addCoins(coins: Int) {
        val newCoins = coins + coinsSharedPreferences.getCoins()
        coinsSharedPreferences.setCoins(newCoins)
    }

    override fun removeCoins(coins: Int) = addCoins(-coins)

    override fun setCoins(coins: Int) = coinsSharedPreferences.setCoins(coins)

    override fun resetCoins() = coinsSharedPreferences.setCoins(50)
}