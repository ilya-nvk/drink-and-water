package com.ilyanvk.drinkwater.data.datasource.plants

import android.content.Context

class CurrentPlantSharedPreferencesImpl(
    private val context: Context
) : CurrentPlantSharedPreferences {
    override fun getCurrentPlantId(): String? {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return prefs.getString(CURRENT_PLANT_ID_KEY, null)
    }

    override fun setCurrentPlantId(id: String) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        with(prefs.edit()) {
            putString(CURRENT_PLANT_ID_KEY, id)
            apply()
        }
    }

    override fun deleteCurrentPlantId() {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        with(prefs.edit()) {
            remove(CURRENT_PLANT_ID_KEY)
            apply()
        }
    }

    companion object {
        private const val PREF_NAME = "CurrentPlantPrefs"
        private const val CURRENT_PLANT_ID_KEY = "current_plant_id"
    }
}