package com.ilyanvk.drinkwater.domain.repository.lastlogin

interface LastLoginRepository {
    fun updateLastLogin()
    fun isFirstLoginToday(): Boolean
    fun isTheVeryFirstLogin(): Boolean
}
