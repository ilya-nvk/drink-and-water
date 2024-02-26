package com.ilyanvk.drinkwater.domain.repository.coins

import android.content.Context
import com.ilyanvk.drinkwater.data.datasource.coins.CoinsSharedPreferences

class CoinsRepositoryImpl(
    private val coinsSharedPreferences: CoinsSharedPreferences,
    private val context: Context
) : CoinsRepository {
    override fun getCoins(): Int = coinsSharedPreferences.getCoins(context)

    override fun addCoins(coins: Int) {
        val newCoins = coins + coinsSharedPreferences.getCoins(context)
        coinsSharedPreferences.setCoins(context, newCoins)
    }

    override fun removeCoins(coins: Int) = addCoins(-coins)

    override fun setCoins(coins: Int) = coinsSharedPreferences.setCoins(context, coins)

    override fun resetCoins() = coinsSharedPreferences.setCoins(context, 0)
}