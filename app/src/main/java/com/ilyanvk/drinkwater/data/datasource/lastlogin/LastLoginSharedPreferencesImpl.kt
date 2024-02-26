package com.ilyanvk.drinkwater.data.datasource.lastlogin

import android.content.Context

class LastLoginSharedPreferencesImpl : LastLoginSharedPreferences {
    override fun saveLastLoginTime(context: Context, time: Long) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        with(prefs.edit()) {
            putLong(KEY_LAST_LOGIN_TIME, time)
            apply()
        }
    }

    override fun getLastLoginTime(context: Context): Long {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return prefs.getLong(KEY_LAST_LOGIN_TIME, 0)
    }

    companion object {
        private const val PREF_NAME = "LoginPrefs"
        private const val KEY_LAST_LOGIN_TIME = "last_login_time"
    }
}