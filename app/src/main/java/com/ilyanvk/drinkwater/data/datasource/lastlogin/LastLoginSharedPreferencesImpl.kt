package com.ilyanvk.drinkwater.data.datasource.lastlogin

import android.content.Context

class LastLoginSharedPreferencesImpl(
    private val context: Context
) : LastLoginSharedPreferences {
    override fun saveLastLoginTime(time: Long) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        with(prefs.edit()) {
            putLong(KEY_LAST_LOGIN_TIME, time)
            apply()
        }
    }

    override fun getLastLoginTime(): Long? {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val lastLoginTime = prefs.getLong(KEY_LAST_LOGIN_TIME, 0)
        return if (lastLoginTime == 0L) null else lastLoginTime
    }

    companion object {
        private const val PREF_NAME = "LoginPrefs"
        private const val KEY_LAST_LOGIN_TIME = "last_login_time"
    }
}
