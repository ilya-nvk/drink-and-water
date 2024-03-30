package com.ilyanvk.drinkwater.di

import android.content.Context
import com.ilyanvk.drinkwater.data.datasource.settings.ThemeSharedPreferences
import com.ilyanvk.drinkwater.data.datasource.settings.ThemeSharedPreferencesImpl
import com.ilyanvk.drinkwater.domain.repository.settings.ThemeRepository
import com.ilyanvk.drinkwater.domain.repository.settings.ThemeRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class SettingsModule {
    @Provides
    fun provideSettingsSharedPreferences(@ApplicationContext context: Context): ThemeSharedPreferences =
        ThemeSharedPreferencesImpl(context)

    @Provides
    fun provideSettingsRepository(themeSharedPreferences: ThemeSharedPreferences): ThemeRepository =
        ThemeRepositoryImpl(themeSharedPreferences)
}
