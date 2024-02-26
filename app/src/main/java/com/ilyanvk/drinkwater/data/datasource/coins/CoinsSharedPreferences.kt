package com.ilyanvk.drinkwater.data.datasource.coins

import android.content.Context

interface CoinsSharedPreferences {
    fun setCoins(context: Context, coins: Int)
    fun getCoins(context: Context): Int

}
