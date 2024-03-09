package com.ilyanvk.drinkwater.di

import android.content.Context
import androidx.room.Room
import com.ilyanvk.drinkwater.data.datasource.intakerecord.IntakeRecordDao
import com.ilyanvk.drinkwater.data.datasource.intakerecord.IntakeRecordDatabase
import com.ilyanvk.drinkwater.domain.repository.intakerecord.IntakeRecordRepository
import com.ilyanvk.drinkwater.domain.repository.intakerecord.IntakeRecordRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class IntakeRecordModule {
    @Provides
    fun provideIntakeRecordDao(
        @ApplicationContext context: Context
    ): IntakeRecordDao {
        return Room.databaseBuilder(
            context, IntakeRecordDatabase::class.java, IntakeRecordDatabase.DATABASE_NAME
        ).build().dao
    }

    @Provides
    fun provideIntakeRecordRepository(
        dao: IntakeRecordDao
    ): IntakeRecordRepository {
        return IntakeRecordRepositoryImpl(dao)
    }
}
