package com.ilyanvk.drinkwater.di

import android.content.Context
import androidx.room.Room
import com.ilyanvk.drinkwater.data.datasource.notifications.NotificationsDao
import com.ilyanvk.drinkwater.data.datasource.notifications.NotificationsDatabase
import com.ilyanvk.drinkwater.domain.repository.notifications.NotificationsRepository
import com.ilyanvk.drinkwater.domain.repository.notifications.NotificationsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class NotificationsModule {
    @Provides
    fun provideNotificationsDao(
        @ApplicationContext context: Context
    ): NotificationsDao {
        return Room.databaseBuilder(
            context, NotificationsDatabase::class.java, NotificationsDatabase.DATABASE_NAME
        ).build().dao
    }

    @Provides
    fun provideNotificationsRepository(dao: NotificationsDao): NotificationsRepository =
        NotificationsRepositoryImpl(dao)
}
