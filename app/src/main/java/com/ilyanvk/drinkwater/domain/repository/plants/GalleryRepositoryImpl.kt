package com.ilyanvk.drinkwater.domain.repository.plants

import com.ilyanvk.drinkwater.data.datasource.plants.GalleryDao
import com.ilyanvk.drinkwater.domain.model.Plant
import kotlinx.coroutines.flow.Flow
import java.util.UUID

class GalleryRepositoryImpl(
    private val dao: GalleryDao
) : GalleryRepository {
    override fun getGrownPlants(): Flow<List<Plant>> = dao.getGrownPlants()

    override suspend fun getGrownPlantById(id: String): Plant? = dao.getGrownPlantById(id)

    override suspend fun addGrownPlant(plant: Plant) =
        dao.insertGrownPlant(plant.copy(id = UUID.randomUUID().toString()))

    override suspend fun deleteGrownPlant(plant: Plant) = dao.deleteGrownPlant(plant)
}