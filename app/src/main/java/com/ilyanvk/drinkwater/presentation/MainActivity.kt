package com.ilyanvk.drinkwater.presentation

import android.Manifest
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.toArgb
import androidx.core.app.ActivityCompat
import com.ilyanvk.drinkwater.domain.model.Theme
import com.ilyanvk.drinkwater.domain.repository.lastlogin.LastLoginRepository
import com.ilyanvk.drinkwater.domain.repository.settings.ThemeRepository
import com.ilyanvk.drinkwater.presentation.onboarding.OnboardingScreen
import com.ilyanvk.drinkwater.ui.theme.DrinkWaterTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var themeRepository: ThemeRepository

    @Inject
    lateinit var lastLoginRepository: LastLoginRepository

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
                var showOnboarding by remember { mutableStateOf(lastLoginRepository.isTheVeryFirstLogin()) }
                if (showOnboarding) {
                    window.navigationBarColor = MaterialTheme.colorScheme.background.toArgb()
                    OnboardingScreen(onFinished = { showOnboarding = false })
                } else {
                    window.navigationBarColor = MaterialTheme.colorScheme.surfaceContainer.toArgb()
                    MainScreen()
                }
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
