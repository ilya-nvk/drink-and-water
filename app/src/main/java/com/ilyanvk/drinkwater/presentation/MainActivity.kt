package com.ilyanvk.drinkwater.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import com.ilyanvk.drinkwater.domain.model.Theme
import com.ilyanvk.drinkwater.domain.repository.settings.ThemeRepository
import com.ilyanvk.drinkwater.ui.theme.DrinkWaterTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var themeRepository: ThemeRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        themeRepository.getTheme().observe(this) { theme ->
            setContent {
                DrinkWaterTheme(
                    darkTheme = when (theme) {
                        Theme.LIGHT -> false
                        Theme.DARK -> true
                        Theme.SYSTEM -> isSystemInDarkTheme()
                        null -> isSystemInDarkTheme()
                    }
                ) {
                    MainScreen()
                }
            }
        }
    }
}