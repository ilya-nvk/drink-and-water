package com.ilyanvk.drinkwater.presentation.shop

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ilyanvk.drinkwater.domain.repository.coins.CoinsRepository
import com.ilyanvk.drinkwater.domain.repository.plants.GalleryRepository
import com.ilyanvk.drinkwater.domain.repository.plants.ShopRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShopViewModel @Inject constructor(
    private val coinsRepository: CoinsRepository,
    private val galleryRepository: GalleryRepository,
    shopRepository: ShopRepository
) : ViewModel() {

    private val _state = mutableStateOf(
        ShopScreenState(
            coins = coinsRepository.getCoins(), plants = shopRepository.getAllPlants()
        )
    )
    val state: State<ShopScreenState> = _state

    init {
        Log.d(TAG, "init")
        galleryRepository.getGrownPlants().launchIn(viewModelScope)
    }

    fun onEvent(event: ShopScreenEvent) {
        when (event) {
            is ShopScreenEvent.BuyPlant -> {
                val plant = event.plant
                val coins = _state.value.coins
                if (coins >= plant.price) {
                    coinsRepository.removeCoins(plant.price)
                    _state.value = _state.value.copy(coins = coinsRepository.getCoins())
                    viewModelScope.launch(Dispatchers.IO) {
                        galleryRepository.setCurrentPlant(plant)
                    }
                } else {
                    throw NotEnoughCoinsException()
                }
            }

            ShopScreenEvent.ShowCoinsDialog -> {
                _state.value = _state.value.copy(showCoinsDialog = true)
            }

            ShopScreenEvent.HideCoinsDialog -> {
                _state.value = _state.value.copy(showCoinsDialog = false)
            }

            is ShopScreenEvent.ShowBuyPlantDialog -> {
                if (galleryRepository.getCurrentPlantId() != null) {
                    throw CurrentlyGrowingPlantException()
                }
                _state.value = _state.value.copy(buyPlantDialog = event.plant)
            }

            ShopScreenEvent.HideBuyPlantDialog -> {
                _state.value = _state.value.copy(buyPlantDialog = null)
            }
        }
    }


    private companion object {
        private const val TAG = "ShopViewModel"
    }
}
