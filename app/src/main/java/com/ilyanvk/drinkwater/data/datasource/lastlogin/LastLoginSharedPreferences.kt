package com.ilyanvk.drinkwater.data.datasource.lastlogin

import android.content.Context

interface LastLoginSharedPreferences {
    fun saveLastLoginTime(context: Context, time: Long)
    fun getLastLoginTime(context: Context): Long
}