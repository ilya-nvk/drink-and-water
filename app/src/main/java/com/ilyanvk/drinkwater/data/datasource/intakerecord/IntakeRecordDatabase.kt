package com.ilyanvk.drinkwater.data.datasource.intakerecord

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ilyanvk.drinkwater.domain.model.IntakeRecord

@Database(
    entities = [IntakeRecord::class], version = 1
)
abstract class IntakeRecordDatabase : RoomDatabase() {
    abstract val dao: IntakeRecordDao

    companion object {
        const val DATABASE_NAME = "intake_record_database"
    }
}