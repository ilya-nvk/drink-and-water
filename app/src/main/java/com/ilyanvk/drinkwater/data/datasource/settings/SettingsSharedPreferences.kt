package com.ilyanvk.drinkwater.data.datasource.settings

import android.content.Context
import com.ilyanvk.drinkwater.domain.model.Theme

interface SettingsSharedPreferences {
    fun setTheme(context: Context, theme: Theme)
    fun getTheme(context: Context): Theme
}