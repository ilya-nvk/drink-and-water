package com.ilyanvk.drinkwater.data.datasource.settings

import com.ilyanvk.drinkwater.domain.model.Theme

interface ThemeSharedPreferences {
    fun setTheme(theme: Theme)
    fun getTheme(): Theme
}