package com.ilyanvk.drinkwater.di

//@Module
//@InstallIn(SingletonComponent::class)
//class AppModule {
//    @Provides
//    fun provideCoinsSharedPreferences(context: Application): CoinsSharedPreferences =
//        CoinsSharedPreferencesImpl(context)
//
//    @Provides
//    fun provideCoinsRepository(coinsSharedPreferences: CoinsSharedPreferences): CoinsRepository =
//        CoinsRepositoryImpl(coinsSharedPreferences)
//
//    @Provides
//    fun provideIntakeRecordDao(
//        context: Application
//    ): IntakeRecordDao {
//        return Room.databaseBuilder(
//            context, IntakeRecordDatabase::class.java, IntakeRecordDatabase.DATABASE_NAME
//        ).build().dao
//    }
//
//    @Provides
//    fun provideIntakeRecordRepository(
//        dao: IntakeRecordDao
//    ): IntakeRecordRepository {
//        return IntakeRecordRepositoryImpl(dao)
//    }
//
//    @Provides
//    fun provideLastLoginSharedPreferences(context: Application): LastLoginSharedPreferences =
//        LastLoginSharedPreferencesImpl(context)
//
//    @Provides
//    fun provideLastLoginRepository(lastLoginSharedPreferences: LastLoginSharedPreferences): LastLoginRepository =
//        LastLoginRepositoryImpl(lastLoginSharedPreferences)
//
//    @Provides
//    fun provideNotificationsDao(
//        context: Application
//    ): NotificationsDao {
//        return Room.databaseBuilder(
//            context, NotificationsDatabase::class.java, NotificationsDatabase.DATABASE_NAME
//        ).build().dao
//    }
//
//    @Provides
//    fun provideNotificationsRepository(dao: NotificationsDao): NotificationsRepository =
//        NotificationsRepositoryImpl(dao)
//
//    @Provides
//    fun provideGalleryDao(
//        context: Application
//    ): GalleryDao {
//        return Room.databaseBuilder(
//            context, GalleryDatabase::class.java, GalleryDatabase.DATABASE_NAME
//        ).build().dao
//    }
//
//    @Provides
//    fun provideGalleryRepository(dao: GalleryDao, ): GalleryRepository = GalleryRepositoryImpl(dao)
//
//    @Provides
//    fun provideSettingsSharedPreferences(context: Application): ThemeSharedPreferences =
//        ThemeSharedPreferencesImpl(context)
//
//    @Provides
//    fun provideSettingsRepository(themeSharedPreferences: ThemeSharedPreferences): ThemeRepository =
//        ThemeRepositoryImpl(themeSharedPreferences)
//
//    @Provides
//    fun provideUserProfileSharedPreferences(context: Application): UserProfileSharedPreferences =
//        UserProfileSharedPreferencesImpl(context)
//
//    @Provides
//    fun provideUserProfileRepository(userProfileSharedPreferences: UserProfileSharedPreferences): UserProfileRepository =
//        UserProfileRepositoryImpl(userProfileSharedPreferences)
//}