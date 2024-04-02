package com.ilyanvk.drinkwater.data.datasource.lastlogin

interface LastLoginSharedPreferences {
    fun saveLastLoginTime(time: Long)
    fun getLastLoginTime(): Long?
}
