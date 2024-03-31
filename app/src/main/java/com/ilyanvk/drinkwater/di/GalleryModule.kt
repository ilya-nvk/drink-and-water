package com.ilyanvk.drinkwater.di

import android.content.Context
import androidx.room.Room
import com.ilyanvk.drinkwater.data.datasource.plants.CurrentPlantSharedPreferences
import com.ilyanvk.drinkwater.data.datasource.plants.CurrentPlantSharedPreferencesImpl
import com.ilyanvk.drinkwater.data.datasource.plants.GalleryDao
import com.ilyanvk.drinkwater.data.datasource.plants.GalleryDatabase
import com.ilyanvk.drinkwater.domain.repository.plants.GalleryRepository
import com.ilyanvk.drinkwater.domain.repository.plants.GalleryRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class GalleryModule {

    @Singleton
    @Provides
    fun provideGalleryDao(
        @ApplicationContext context: Context
    ): GalleryDao {
        return Room.databaseBuilder(
            context, GalleryDatabase::class.java, GalleryDatabase.DATABASE_NAME
        ).build().dao
    }

    @Singleton
    @Provides
    fun provideCurrentPlantSharedPreferences(
        @ApplicationContext context: Context
    ): CurrentPlantSharedPreferences = CurrentPlantSharedPreferencesImpl(context)

    @Singleton
    @Provides
    fun provideGalleryRepository(
        dao: GalleryDao, currentPlantSharedPreferences: CurrentPlantSharedPreferences
    ): GalleryRepository = GalleryRepositoryImpl(dao, currentPlantSharedPreferences)
}
