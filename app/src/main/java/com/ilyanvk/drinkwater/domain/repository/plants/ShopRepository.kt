package com.ilyanvk.drinkwater.domain.repository.plants

import com.ilyanvk.drinkwater.domain.model.Plant

interface ShopRepository {
    fun getAllPlants(): List<Plant>
    fun getPlantById(id: String): Plant?
}