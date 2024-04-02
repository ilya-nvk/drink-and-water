package com.ilyanvk.drinkwater.di

import android.content.Context
import com.ilyanvk.drinkwater.data.datasource.userprofile.UserProfileSharedPreferences
import com.ilyanvk.drinkwater.data.datasource.userprofile.UserProfileSharedPreferencesImpl
import com.ilyanvk.drinkwater.domain.repository.userprofile.UserProfileRepository
import com.ilyanvk.drinkwater.domain.repository.userprofile.UserProfileRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UserProfileModule {

    @Singleton
    @Provides
    fun provideUserProfileSharedPreferences(@ApplicationContext context: Context): UserProfileSharedPreferences =
        UserProfileSharedPreferencesImpl(context)

    @Singleton
    @Provides
    fun provideUserProfileRepository(userProfileSharedPreferences: UserProfileSharedPreferences): UserProfileRepository =
        UserProfileRepositoryImpl(userProfileSharedPreferences)
}
