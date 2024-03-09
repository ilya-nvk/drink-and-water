package com.ilyanvk.drinkwater.presentation.gallery

import com.ilyanvk.drinkwater.domain.model.Plant

data class GalleryScreenState(
    val plants: List<Plant> = emptyList(),
    val deletePlantDialog: Plant? = null
)
