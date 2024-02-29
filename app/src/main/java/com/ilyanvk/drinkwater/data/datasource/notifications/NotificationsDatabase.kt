package com.ilyanvk.drinkwater.data.datasource.notifications

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ilyanvk.drinkwater.domain.model.Notification

@Database(
    entities = [Notification::class], version = 1
)
abstract class NotificationsDatabase : RoomDatabase() {
    abstract val dao: NotificationsDao

    companion object {
        const val DATABASE_NAME = "notifications_database"
    }
}