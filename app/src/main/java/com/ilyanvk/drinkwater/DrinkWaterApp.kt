package com.ilyanvk.drinkwater

import android.app.Application
import com.ilyanvk.drinkwater.presentation.notifications.DrinkNotificationManager
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class DrinkWaterApp : Application() {

    @Inject
    lateinit var drinkNotificationManager: DrinkNotificationManager

    override fun onCreate() {
        super.onCreate()
        drinkNotificationManager.createNotificationChannel()
    }
}
