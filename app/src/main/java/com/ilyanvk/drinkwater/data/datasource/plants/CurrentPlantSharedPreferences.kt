package com.ilyanvk.drinkwater.data.datasource.plants

interface CurrentPlantSharedPreferences {
    fun getCurrentPlantId(): String?
    fun setCurrentPlantId(id: String)
    fun deleteCurrentPlantId()
}