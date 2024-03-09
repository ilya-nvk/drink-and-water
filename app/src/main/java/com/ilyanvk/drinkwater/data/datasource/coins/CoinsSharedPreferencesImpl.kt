package com.ilyanvk.drinkwater.data.datasource.coins

import android.content.Context

class CoinsSharedPreferencesImpl(
    private val context: Context
) : CoinsSharedPreferences {
    override fun setCoins(coins: Int) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        with(prefs.edit()) {
            putInt(KEY_COINS, coins)
            apply()
        }
    }

    override fun getCoins(): Int {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return prefs.getInt(KEY_COINS, 0)
    }

    companion object {
        private const val PREF_NAME = "CoinsPrefs"
        private const val KEY_COINS = "coins"
    }
}