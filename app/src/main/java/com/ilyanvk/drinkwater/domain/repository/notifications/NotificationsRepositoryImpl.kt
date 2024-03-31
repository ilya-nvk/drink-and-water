package com.ilyanvk.drinkwater.domain.repository.notifications

import com.ilyanvk.drinkwater.data.datasource.notifications.NotificationsDao
import com.ilyanvk.drinkwater.domain.model.Notification
import com.ilyanvk.drinkwater.presentation.notifications.DrinkNotificationManager
import kotlinx.coroutines.flow.Flow

class NotificationsRepositoryImpl(
    private val dao: NotificationsDao,
    private val notificationManager: DrinkNotificationManager
) : NotificationsRepository {
    override fun getNotifications(): Flow<List<Notification>> {
        return dao.getNotifications()
    }

    override suspend fun getNotificationById(id: Int): Notification? {
        return dao.getNotificationById(id)
    }

    override suspend fun addNotification(notification: Notification) {
        notificationManager.setNotification(notification)
        dao.insertNotification(notification)
    }

    override suspend fun updateNotification(notification: Notification) {
        notificationManager.cancelNotification(notification.id)
        if (notification.isActive) {
            notificationManager.setNotification(notification)
        }
        dao.insertNotification(notification)
    }

    override suspend fun deleteNotification(notification: Notification) {
        notificationManager.cancelNotification(notification.id)
        dao.deleteNotification(notification)
    }

    override suspend fun clear() {
        dao.getNotifications().collect { notifications ->
            notifications.forEach { deleteNotification(it) }
        }
    }
}
