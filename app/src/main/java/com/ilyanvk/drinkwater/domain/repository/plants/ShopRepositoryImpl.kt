package com.ilyanvk.drinkwater.domain.repository.plants

import com.ilyanvk.drinkwater.R
import com.ilyanvk.drinkwater.domain.model.Plant

class ShopRepositoryImpl : ShopRepository {
    private val plants = listOf(
        Plant(
            R.string.apple,
            0,
            500,
            R.drawable.apple_seed,
            R.drawable.apple_sprout,
            R.drawable.apple_teen,
            R.drawable.apple_adult
        ),
        Plant(
            R.string.cactus,
            5,
            500,
            R.drawable.cactus_seed,
            R.drawable.cactus_sprout,
            R.drawable.cactus_teen,
            R.drawable.cactus_adult
        ),
        Plant(
            R.string.purple_tulip,
            10,
            500,
            R.drawable.purple_tulip_seed,
            R.drawable.purple_tulip_sprout,
            R.drawable.purple_tulip_teen,
            R.drawable.purple_tulip_adult
        )
    )

    override fun getAllPlants(): List<Plant> = plants

    override fun getPlantById(id: String): Plant? = plants.find { it.id == id }
}
