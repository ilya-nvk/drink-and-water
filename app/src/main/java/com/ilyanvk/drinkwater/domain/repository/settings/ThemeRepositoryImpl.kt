package com.ilyanvk.drinkwater.domain.repository.settings

import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ilyanvk.drinkwater.data.datasource.settings.ThemeSharedPreferences
import com.ilyanvk.drinkwater.domain.model.Theme

class ThemeRepositoryImpl(
    private val sharedPreferences: ThemeSharedPreferences
) : ThemeRepository {

    private val themeLiveData = MutableLiveData(sharedPreferences.getTheme())

    init {
        Log.d("ThemeRepositoryImpl", "init")
        themeLiveData.observeForever {
            Log.d("ThemeRepositoryImpl", "themeLiveData.observeForever")
        }
    }

    init {
        updateTheme(sharedPreferences.getTheme())
    }

    override fun getTheme(): LiveData<Theme> {
        return themeLiveData
    }

    override fun setTheme(theme: Theme) {
        updateTheme(theme)
        sharedPreferences.setTheme(theme)
    }

    private fun updateTheme(theme: Theme) {
        themeLiveData.value = theme
        when (theme) {
            Theme.LIGHT -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            Theme.DARK -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            Theme.SYSTEM -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        }
    }
}
