package com.ilyanvk.drinkwater.presentation.gallery

import com.ilyanvk.drinkwater.domain.model.Plant

sealed class GalleryScreenEvent {
    data class DeletePlant(val plant: Plant) : GalleryScreenEvent()
    data object RestorePlant : GalleryScreenEvent()
    data class ShowDeletePlantDialog(val plant: Plant?) : GalleryScreenEvent()
    data object HideDeletePlantDialog : GalleryScreenEvent()
}