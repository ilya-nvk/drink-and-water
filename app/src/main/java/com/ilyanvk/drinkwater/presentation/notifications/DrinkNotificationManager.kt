package com.ilyanvk.drinkwater.presentation.notifications

import com.ilyanvk.drinkwater.domain.model.Notification

interface DrinkNotificationManager {
    fun createNotificationChannel()
    fun setNotification(notification: Notification)
    fun cancelNotification(notificationId: String)

    companion object {
        const val CHANNEL_ID = "DRINK_REMINDERS_CHANNEL"
    }
}
