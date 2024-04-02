package com.ilyanvk.drinkwater.data.datasource.notifications

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ilyanvk.drinkwater.domain.model.Notification
import kotlinx.coroutines.flow.Flow

@Dao
interface NotificationsDao {
    @Query("SELECT * FROM notification")
    fun getNotifications(): Flow<List<Notification>>

    @Query("SELECT * FROM notification WHERE id = :id")
    suspend fun getNotificationById(id: Int): Notification?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNotification(notification: Notification)

    @Delete
    suspend fun deleteNotification(notification: Notification)
}