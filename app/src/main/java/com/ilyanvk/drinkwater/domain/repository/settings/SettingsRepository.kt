package com.ilyanvk.drinkwater.domain.repository.settings

import com.ilyanvk.drinkwater.domain.model.Theme

interface SettingsRepository {
    fun setTheme(theme: Theme)
    fun getTheme(): Theme
}