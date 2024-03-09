package com.ilyanvk.drinkwater.presentation.gallery

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ilyanvk.drinkwater.domain.model.Plant
import com.ilyanvk.drinkwater.domain.repository.plants.GalleryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(
    private val repository: GalleryRepository
) : ViewModel() {
    private val _state = mutableStateOf(GalleryScreenState())
    val state: State<GalleryScreenState> = _state

    private var lastDeletedPlants = mutableListOf<Plant>()

    init {
        repository.getGrownPlants().onEach { plants ->
            _state.value = _state.value.copy(plants = plants)
        }.launchIn(viewModelScope)
    }

    fun onEvent(event: GalleryScreenEvent) {
        when (event) {
            is GalleryScreenEvent.DeletePlant -> {
                viewModelScope.launch(Dispatchers.IO) {
                    lastDeletedPlants.add(event.plant)
                    repository.deleteGrownPlant(event.plant)
                }
            }

            GalleryScreenEvent.RestorePlant -> {
                viewModelScope.launch(Dispatchers.IO) {
                    lastDeletedPlants.lastOrNull()?.let {
                        repository.addGrownPlant(it)
                    }
                    lastDeletedPlants.removeLastOrNull()
                }
            }

            is GalleryScreenEvent.ShowDeletePlantDialog -> {
                _state.value = _state.value.copy(deletePlantDialog = event.plant)
            }

            GalleryScreenEvent.HideDeletePlantDialog -> {
                _state.value = _state.value.copy(deletePlantDialog = null)
            }
        }
    }
}