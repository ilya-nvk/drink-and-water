package com.ilyanvk.drinkwater.data.datasource.settings

import android.content.Context
import com.ilyanvk.drinkwater.domain.model.Theme

class SettingsSharedPreferencesImpl : SettingsSharedPreferences {
    override fun setTheme(context: Context, theme: Theme) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        with(prefs.edit()) {
            putInt(THEME_KEY, theme.ordinal)
            apply()
        }
    }

    override fun getTheme(context: Context): Theme {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val themeOrdinal = prefs.getInt(THEME_KEY, Theme.SYSTEM.ordinal)
        return Theme.entries[themeOrdinal]
    }

    companion object {
        private const val PREF_NAME = "SettingPrefs"
        private const val THEME_KEY = "theme"
    }
}