package com.ilyanvk.drinkwater.data.datasource.intakerecord

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ilyanvk.drinkwater.domain.model.IntakeRecord
import kotlinx.coroutines.flow.Flow

@Dao
interface IntakeRecordDao {
    @Query("SELECT * FROM intakerecord")
    fun getIntakeRecords(): Flow<List<IntakeRecord>>

    @Query("SELECT * FROM intakerecord WHERE id = :id")
    suspend fun getIntakeRecordById(id: String): IntakeRecord?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIntakeRecord(intakeRecord: IntakeRecord)

    @Delete
    suspend fun deleteIntakeRecord(intakeRecord: IntakeRecord)
}