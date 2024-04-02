package com.ilyanvk.drinkwater.data.datasource.plants

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ilyanvk.drinkwater.domain.model.Plant

@Database(
    entities = [Plant::class], version = 1
)
abstract class GalleryDatabase : RoomDatabase() {
    abstract val dao: GalleryDao

    companion object {
        const val DATABASE_NAME = "galley_database"
    }
}