package com.ilyanvk.drinkwater.domain.repository.plants

import com.ilyanvk.drinkwater.data.datasource.plants.CurrentPlantSharedPreferences
import com.ilyanvk.drinkwater.data.datasource.plants.GalleryDao
import com.ilyanvk.drinkwater.domain.model.Plant
import kotlinx.coroutines.flow.Flow
import java.util.UUID

class GalleryRepositoryImpl(
    private val dao: GalleryDao,
    private val currentPlantSharedPreferences: CurrentPlantSharedPreferences
) : GalleryRepository {
    override fun getGrownPlants(): Flow<List<Plant>> =
        dao.getGrownPlants()

    override suspend fun getGrownPlantById(id: String): Plant? = dao.getGrownPlantById(id)

    override suspend fun addGrownPlant(plant: Plant) =
        dao.insertGrownPlant(plant.copy(id = UUID.randomUUID().toString()))

    override suspend fun deleteGrownPlant(plant: Plant) = dao.deleteGrownPlant(plant)
    override fun getCurrentPlantId(): String? = currentPlantSharedPreferences.getCurrentPlantId()

    override suspend fun getCurrentPlant(): Plant? {
        val id = currentPlantSharedPreferences.getCurrentPlantId()
        return id?.let { dao.getGrownPlantById(it) }
    }

    override suspend fun setCurrentPlant(plant: Plant) {
        val id = UUID.randomUUID().toString()
        currentPlantSharedPreferences.setCurrentPlantId(id)
        updateCurrentPlant(plant.copy(id = id))
    }

    override suspend fun updateCurrentPlant(plant: Plant) {
        dao.insertGrownPlant(plant)
    }

    override suspend fun clear() {
        deleteCurrentPlant()
        dao.clear()
    }

    override fun deleteCurrentPlant() {
        currentPlantSharedPreferences.deleteCurrentPlantId()
    }
}
