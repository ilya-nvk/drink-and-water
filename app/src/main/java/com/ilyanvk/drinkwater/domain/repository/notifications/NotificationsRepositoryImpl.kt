package com.ilyanvk.drinkwater.domain.repository.notifications

import com.ilyanvk.drinkwater.data.datasource.notifications.NotificationsDao
import com.ilyanvk.drinkwater.domain.model.Notification
import kotlinx.coroutines.flow.Flow

class NotificationsRepositoryImpl(
    private val dao: NotificationsDao
) : NotificationsRepository {
    override fun getNotifications(): Flow<List<Notification>> {
        return dao.getNotifications()
    }

    override suspend fun getNotificationById(id: Int): Notification? {
        return dao.getNotificationById(id)
    }

    override suspend fun addNotification(notification: Notification) {
        dao.insertNotification(notification)
    }

    override suspend fun updateNotification(notification: Notification) {
        // todo
    }

    override suspend fun deleteNotification(notification: Notification) {
        dao.deleteNotification(notification)
    }
}