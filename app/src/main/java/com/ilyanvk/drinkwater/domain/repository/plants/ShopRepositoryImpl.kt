package com.ilyanvk.drinkwater.domain.repository.plants

import com.ilyanvk.drinkwater.R
import com.ilyanvk.drinkwater.domain.model.Plant

class ShopRepositoryImpl : ShopRepository {
    private val plants = listOf(
        Plant(R.string.dandelion, 0, 500, 0, 0, 0, R.drawable.plant_test),
        Plant(R.string.rose, 5, 1000, 0, 0, 0, R.drawable.plant_test),
        Plant(R.string.tulip, 3, 800, 0, 0, 0, R.drawable.plant_test),
        Plant(R.string.sunflower, 4, 1200, 0, 0, 0, R.drawable.plant_test),
        Plant(R.string.orchid, 6, 1500, 0, 0, 0, R.drawable.plant_test),
        Plant(R.string.lily, 7, 1400, 0, 0, 0, R.drawable.plant_test),
        Plant(R.string.daisy, 2, 600, 0, 0, 0, R.drawable.plant_test),
        Plant(R.string.lavender, 4, 900, 0, 0, 0, R.drawable.plant_test),
        Plant(R.string.jasmine, 5, 1100, 0, 0, 0, R.drawable.plant_test),
        Plant(R.string.peony, 8, 1700, 0, 0, 0, R.drawable.plant_test),
        Plant(R.string.daffodil, 3, 700, 0, 0, 0, R.drawable.plant_test),
        Plant(R.string.marigold, 2, 600, 0, 0, 0, R.drawable.plant_test),
        Plant(R.string.hydrangea, 7, 1300, 0, 0, 0, R.drawable.plant_test),
        Plant(R.string.poinsettia, 6, 1400, 0, 0, 0, R.drawable.plant_test),
        Plant(R.string.hibiscus, 5, 1000, 0, 0, 0, R.drawable.plant_test),
        Plant(R.string.geranium, 4, 800, 0, 0, 0, R.drawable.plant_test),
        Plant(R.string.carnation, 3, 900, 0, 0, 0, R.drawable.plant_test),
        Plant(R.string.azalea, 6, 1200, 0, 0, 0, R.drawable.plant_test),
        Plant(R.string.iris, 5, 1000, 0, 0, 0, R.drawable.plant_test),
        Plant(R.string.chrysanthemum, 7, 1300, 0, 0, 0, R.drawable.plant_test),
        Plant(R.string.snapdragon, 6, 1100, 0, 0, 0, R.drawable.plant_test)
    )

    override fun getAllPlants(): List<Plant> = plants

    override fun getPlantById(id: String): Plant? = plants.find { it.id == id }
}