package com.ilyanvk.drinkwater.domain.repository.intakerecord

import com.ilyanvk.drinkwater.data.datasource.intakerecord.IntakeRecordDao
import com.ilyanvk.drinkwater.domain.model.IntakeRecord
import kotlinx.coroutines.flow.Flow

class IntakeRecordRepositoryImpl(
    private val dao: IntakeRecordDao
) : IntakeRecordRepository {
    override fun getIntakeRecords(): Flow<List<IntakeRecord>> = dao.getIntakeRecords()

    override suspend fun getIntakeRecordById(id: String): IntakeRecord? =
        dao.getIntakeRecordById(id)

    override suspend fun addIntakeRecord(intakeRecord: IntakeRecord) =
        dao.insertIntakeRecord(intakeRecord)

    override suspend fun deleteIntakeRecord(intakeRecord: IntakeRecord) =
        dao.deleteIntakeRecord(intakeRecord)

    override suspend fun clear() = dao.clear()
}
