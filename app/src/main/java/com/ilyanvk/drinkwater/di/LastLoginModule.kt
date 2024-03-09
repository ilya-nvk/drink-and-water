package com.ilyanvk.drinkwater.di

import android.content.Context
import com.ilyanvk.drinkwater.data.datasource.lastlogin.LastLoginSharedPreferences
import com.ilyanvk.drinkwater.data.datasource.lastlogin.LastLoginSharedPreferencesImpl
import com.ilyanvk.drinkwater.domain.repository.lastlogin.LastLoginRepository
import com.ilyanvk.drinkwater.domain.repository.lastlogin.LastLoginRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class LastLoginModule {
    @Provides
    fun provideLastLoginSharedPreferences(@ApplicationContext context: Context): LastLoginSharedPreferences =
        LastLoginSharedPreferencesImpl(context)

    @Provides
    fun provideLastLoginRepository(lastLoginSharedPreferences: LastLoginSharedPreferences): LastLoginRepository =
        LastLoginRepositoryImpl(lastLoginSharedPreferences)
}
