package com.ilyanvk.drinkwater.di

import android.app.Application
import androidx.room.Room
import com.google.android.datatransport.runtime.dagger.Provides
import com.ilyanvk.drinkwater.data.datasource.coins.CoinsSharedPreferences
import com.ilyanvk.drinkwater.data.datasource.coins.CoinsSharedPreferencesImpl
import com.ilyanvk.drinkwater.data.datasource.intakerecord.IntakeRecordDao
import com.ilyanvk.drinkwater.data.datasource.intakerecord.IntakeRecordDatabase
import com.ilyanvk.drinkwater.data.datasource.lastlogin.LastLoginSharedPreferences
import com.ilyanvk.drinkwater.data.datasource.lastlogin.LastLoginSharedPreferencesImpl
import com.ilyanvk.drinkwater.data.datasource.notifications.NotificationsDao
import com.ilyanvk.drinkwater.data.datasource.notifications.NotificationsDatabase
import com.ilyanvk.drinkwater.data.datasource.plants.GalleryDao
import com.ilyanvk.drinkwater.data.datasource.plants.GalleryDatabase
import com.ilyanvk.drinkwater.data.datasource.settings.ThemeSharedPreferences
import com.ilyanvk.drinkwater.data.datasource.settings.ThemeSharedPreferencesImpl
import com.ilyanvk.drinkwater.data.datasource.userprofile.UserProfileSharedPreferences
import com.ilyanvk.drinkwater.data.datasource.userprofile.UserProfileSharedPreferencesImpl
import com.ilyanvk.drinkwater.domain.repository.coins.CoinsRepository
import com.ilyanvk.drinkwater.domain.repository.coins.CoinsRepositoryImpl
import com.ilyanvk.drinkwater.domain.repository.intakerecord.IntakeRecordRepository
import com.ilyanvk.drinkwater.domain.repository.intakerecord.IntakeRecordRepositoryImpl
import com.ilyanvk.drinkwater.domain.repository.lastlogin.LastLoginRepository
import com.ilyanvk.drinkwater.domain.repository.lastlogin.LastLoginRepositoryImpl
import com.ilyanvk.drinkwater.domain.repository.notifications.NotificationsRepository
import com.ilyanvk.drinkwater.domain.repository.notifications.NotificationsRepositoryImpl
import com.ilyanvk.drinkwater.domain.repository.plants.GalleryRepository
import com.ilyanvk.drinkwater.domain.repository.plants.GalleryRepositoryImpl
import com.ilyanvk.drinkwater.domain.repository.settings.ThemeRepository
import com.ilyanvk.drinkwater.domain.repository.settings.ThemeRepositoryImpl
import com.ilyanvk.drinkwater.domain.repository.userprofile.UserProfileRepository
import com.ilyanvk.drinkwater.domain.repository.userprofile.UserProfileRepositoryImpl
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    fun provideCoinsSharedPreferences(context: Application): CoinsSharedPreferences =
        CoinsSharedPreferencesImpl(context)

    @Provides
    fun provideCoinsRepository(coinsSharedPreferences: CoinsSharedPreferences): CoinsRepository =
        CoinsRepositoryImpl(coinsSharedPreferences)

    @Provides
    fun provideIntakeRecordDao(
        context: Application
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

    @Provides
    fun provideLastLoginSharedPreferences(context: Application): LastLoginSharedPreferences =
        LastLoginSharedPreferencesImpl(context)

    @Provides
    fun provideLastLoginRepository(lastLoginSharedPreferences: LastLoginSharedPreferences): LastLoginRepository =
        LastLoginRepositoryImpl(lastLoginSharedPreferences)

    @Provides
    fun provideNotificationsDao(
        context: Application
    ): NotificationsDao {
        return Room.databaseBuilder(
            context, NotificationsDatabase::class.java, NotificationsDatabase.DATABASE_NAME
        ).build().dao
    }

    @Provides
    fun provideNotificationsRepository(dao: NotificationsDao): NotificationsRepository =
        NotificationsRepositoryImpl(dao)

    @Provides
    fun provideGalleryDao(
        context: Application
    ): GalleryDao {
        return Room.databaseBuilder(
            context, GalleryDatabase::class.java, GalleryDatabase.DATABASE_NAME
        ).build().dao
    }

    @Provides
    fun provideGalleryRepository(dao: GalleryDao): GalleryRepository = GalleryRepositoryImpl(dao)

    @Provides
    fun provideSettingsSharedPreferences(context: Application): ThemeSharedPreferences =
        ThemeSharedPreferencesImpl(context)

    @Provides
    fun provideSettingsRepository(themeSharedPreferences: ThemeSharedPreferences): ThemeRepository =
        ThemeRepositoryImpl(themeSharedPreferences)

    @Provides
    fun provideUserProfileSharedPreferences(context: Application): UserProfileSharedPreferences =
        UserProfileSharedPreferencesImpl(context)

    @Provides
    fun provideUserProfileRepository(userProfileSharedPreferences: UserProfileSharedPreferences): UserProfileRepository =
        UserProfileRepositoryImpl(userProfileSharedPreferences)
}