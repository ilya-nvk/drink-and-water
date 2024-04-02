package com.ilyanvk.drinkwater.domain.repository.coins

interface CoinsRepository {
    fun getCoins(): Int
    fun addCoins(coins: Int)
    fun removeCoins(coins: Int)
    fun setCoins(coins: Int)
    fun resetCoins()
}