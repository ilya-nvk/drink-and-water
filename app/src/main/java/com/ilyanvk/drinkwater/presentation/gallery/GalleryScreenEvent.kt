package com.ilyanvk.drinkwater.presentation.gallery

sealed class GalleryScreenEvent {
    data class DeletePlant(val id: String) : GalleryScreenEvent()
    data object RestorePlant : GalleryScreenEvent()
}