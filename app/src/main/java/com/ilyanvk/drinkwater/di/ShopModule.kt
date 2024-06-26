package com.ilyanvk.drinkwater.di

import com.ilyanvk.drinkwater.domain.repository.plants.ShopRepository
import com.ilyanvk.drinkwater.domain.repository.plants.ShopRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ShopModule {

    @Singleton
    @Provides
    fun provideShopRepository(): ShopRepository {
        return ShopRepositoryImpl()
    }
}
