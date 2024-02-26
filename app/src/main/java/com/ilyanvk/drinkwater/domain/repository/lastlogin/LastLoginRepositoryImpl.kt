package com.ilyanvk.drinkwater.domain.repository.lastlogin

import android.content.Context
import com.ilyanvk.drinkwater.data.datasource.lastlogin.LastLoginSharedPreferences
import java.util.Calendar

class LastLoginRepositoryImpl(
    private val lastLoginSharedPreferences: LastLoginSharedPreferences, private val context: Context
) : LastLoginRepository {
    override fun updateLastLogin() =
        lastLoginSharedPreferences.saveLastLoginTime(context, System.currentTimeMillis())

    override fun isFirstLoginToday(): Boolean {
        val midnightMillis = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }.timeInMillis

        return lastLoginSharedPreferences.getLastLoginTime(context) < midnightMillis
    }
}