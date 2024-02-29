package com.ilyanvk.drinkwater.domain.repository.settings

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import com.ilyanvk.drinkwater.data.datasource.settings.SettingsSharedPreferences
import com.ilyanvk.drinkwater.domain.model.Theme

class SettingsRepositoryImpl(
    private val context: Context, private val sharedPreferences: SettingsSharedPreferences
) : SettingsRepository {
    override fun setTheme(theme: Theme) {
        AppCompatDelegate.setDefaultNightMode(
            when (theme) {
                Theme.DARK -> AppCompatDelegate.MODE_NIGHT_YES
                Theme.SYSTEM -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
                Theme.LIGHT -> AppCompatDelegate.MODE_NIGHT_NO
            }
        )
        sharedPreferences.setTheme(context, theme)
    }

    override fun getTheme(): Theme = sharedPreferences.getTheme(context)
}