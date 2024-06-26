package com.ilyanvk.drinkwater.di

import android.content.Context
import com.ilyanvk.drinkwater.data.datasource.coins.CoinsSharedPreferences
import com.ilyanvk.drinkwater.data.datasource.coins.CoinsSharedPreferencesImpl
import com.ilyanvk.drinkwater.domain.repository.coins.CoinsRepository
import com.ilyanvk.drinkwater.domain.repository.coins.CoinsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CoinsModule {

    @Singleton
    @Provides
    fun provideCoinsSharedPreferences(@ApplicationContext context: Context): CoinsSharedPreferences =
        CoinsSharedPreferencesImpl(context)

    @Singleton
    @Provides
    fun provideCoinsRepository(coinsSharedPreferences: CoinsSharedPreferences): CoinsRepository =
        CoinsRepositoryImpl(coinsSharedPreferences)
}
