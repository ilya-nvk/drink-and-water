package com.ilyanvk.drinkwater.presentation

import android.Manifest
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.core.app.ActivityCompat
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
        setContent {
            val theme by themeRepository.getTheme().observeAsState()
            DrinkWaterTheme(
                darkTheme = when (theme) {
                    Theme.LIGHT -> false
                    Theme.DARK -> true
                    else -> isSystemInDarkTheme()
                }
            ) {
                MainScreen()
            }
        }

        requestNotificationPermission()
    }

    private fun requestNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                1
            )
        }
    }
}
