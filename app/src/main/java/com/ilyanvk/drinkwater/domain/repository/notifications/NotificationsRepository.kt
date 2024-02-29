package com.ilyanvk.drinkwater.domain.repository.notifications

import com.ilyanvk.drinkwater.domain.model.Notification
import kotlinx.coroutines.flow.Flow

interface NotificationsRepository {
    fun getNotifications(): Flow<List<Notification>>
    suspend fun getNotificationById(id: Int): Notification?
    suspend fun addNotification(notification: Notification)
    suspend fun updateNotification(notification: Notification)
    suspend fun deleteNotification(notification: Notification)
}