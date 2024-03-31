package com.ilyanvk.drinkwater.domain.repository.plants

import com.ilyanvk.drinkwater.domain.model.Plant
import kotlinx.coroutines.flow.Flow

interface GalleryRepository {
    fun getGrownPlants(): Flow<List<Plant>>
    suspend fun getGrownPlantById(id: String): Plant?
    suspend fun addGrownPlant(plant: Plant)
    suspend fun deleteGrownPlant(plant: Plant)
    fun getCurrentPlantId(): String?
    suspend fun getCurrentPlant(): Plant?
    suspend fun setCurrentPlant(plant: Plant)
    fun deleteCurrentPlant()
    suspend fun updateCurrentPlant(plant: Plant)
    suspend fun clear()
}
