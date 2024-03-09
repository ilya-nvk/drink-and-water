package com.ilyanvk.drinkwater.domain.repository.lastlogin

import com.ilyanvk.drinkwater.data.datasource.lastlogin.LastLoginSharedPreferences
import java.util.Calendar

class LastLoginRepositoryImpl(
    private val lastLoginSharedPreferences: LastLoginSharedPreferences
) : LastLoginRepository {
    override fun updateLastLogin() =
        lastLoginSharedPreferences.saveLastLoginTime(System.currentTimeMillis())

    override fun isFirstLoginToday(): Boolean {
        val midnightMillis = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }.timeInMillis

        return lastLoginSharedPreferences.getLastLoginTime() < midnightMillis
    }
}