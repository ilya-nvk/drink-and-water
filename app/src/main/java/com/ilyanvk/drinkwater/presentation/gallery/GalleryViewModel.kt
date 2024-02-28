package com.ilyanvk.drinkwater.presentation.gallery

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.ilyanvk.drinkwater.domain.model.Plant
import com.ilyanvk.drinkwater.domain.repository.plants.ShopRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor() : ViewModel() {
    private val _plants = mutableStateOf<List<Plant>>(ShopRepositoryImpl().getAllPlants())
    val plants: State<List<Plant>> = _plants

    fun onEvent(event: GalleryScreenEvent) {
        when (event) {
            is GalleryScreenEvent.DeletePlant -> TODO()
            GalleryScreenEvent.RestorePlant -> TODO()
        }
    }
}