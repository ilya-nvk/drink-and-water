package com.ilyanvk.drinkwater.data.datasource.settings

import android.content.Context
import android.util.Log
import com.ilyanvk.drinkwater.domain.model.Theme

class ThemeSharedPreferencesImpl(
    private val context: Context
) : ThemeSharedPreferences {

    init {
        Log.d(TAG, "init")
    }
    override fun setTheme(theme: Theme) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        with(prefs.edit()) {
            putInt(THEME_KEY, theme.ordinal)
            apply()
        }
    }

    override fun getTheme(): Theme {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val themeOrdinal = prefs.getInt(THEME_KEY, Theme.SYSTEM.ordinal)
        return Theme.entries[themeOrdinal]
    }

    private companion object {
        private const val PREF_NAME = "SettingPrefs"
        private const val THEME_KEY = "theme"
        private const val TAG = "ThemeSharedPreferencesImpl"
    }
}
