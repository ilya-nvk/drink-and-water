package com.ilyanvk.drinkwater.domain.repository.intakerecord

import com.ilyanvk.drinkwater.domain.model.IntakeRecord
import kotlinx.coroutines.flow.Flow

interface IntakeRecordRepository {
    fun getIntakeRecords(): Flow<List<IntakeRecord>>
    suspend fun getIntakeRecordById(id: String): IntakeRecord?
    suspend fun addIntakeRecord(intakeRecord: IntakeRecord)
    suspend fun deleteIntakeRecord(intakeRecord: IntakeRecord)
}