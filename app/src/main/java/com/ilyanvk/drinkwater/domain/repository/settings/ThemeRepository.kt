package com.ilyanvk.drinkwater.domain.repository.settings

import androidx.lifecycle.LiveData
import com.ilyanvk.drinkwater.domain.model.Theme

interface ThemeRepository {
    fun setTheme(theme: Theme)
    fun getTheme(): LiveData<Theme>
}
