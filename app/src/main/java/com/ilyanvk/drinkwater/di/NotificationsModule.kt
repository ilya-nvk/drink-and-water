package com.ilyanvk.drinkwater.di

import android.content.Context
import androidx.room.Room
import com.ilyanvk.drinkwater.data.datasource.notifications.NotificationsDao
import com.ilyanvk.drinkwater.data.datasource.notifications.NotificationsDatabase
import com.ilyanvk.drinkwater.domain.repository.notifications.NotificationsRepository
import com.ilyanvk.drinkwater.domain.repository.notifications.NotificationsRepositoryImpl
import com.ilyanvk.drinkwater.presentation.notifications.DrinkNotificationManager
import com.ilyanvk.drinkwater.presentation.notifications.DrinkNotificationManagerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NotificationsModule {

    @Singleton
    @Provides
    fun provideNotificationsDao(
        @ApplicationContext context: Context
    ): NotificationsDao {
        return Room.databaseBuilder(
            context, NotificationsDatabase::class.java, NotificationsDatabase.DATABASE_NAME
        ).build().dao
    }

    @Singleton
    @Provides
    fun provideDrinkNotificationManager(@ApplicationContext context: Context): DrinkNotificationManager =
        DrinkNotificationManagerImpl(context)

    @Singleton
    @Provides
    fun provideNotificationsRepository(
        dao: NotificationsDao,
        notificationManager: DrinkNotificationManager
    ): NotificationsRepository =
        NotificationsRepositoryImpl(dao, notificationManager)
}
