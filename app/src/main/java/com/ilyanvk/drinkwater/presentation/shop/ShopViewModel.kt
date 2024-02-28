package com.ilyanvk.drinkwater.presentation.shop

import androidx.compose.runtime.IntState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.ilyanvk.drinkwater.domain.model.Plant
import com.ilyanvk.drinkwater.domain.repository.plants.ShopRepository
import com.ilyanvk.drinkwater.domain.repository.plants.ShopRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ShopViewModel @Inject constructor() : ViewModel() {
    private val shopRepository: ShopRepository = ShopRepositoryImpl()

    private val _coins = mutableIntStateOf(10)
    val coins: IntState = _coins

    private val _plants = mutableStateOf(shopRepository.getAllPlants())
    val plants: State<List<Plant>> = _plants

    fun onEvent(event: ShopScreenEvent) {
        when (event) {
            is ShopScreenEvent.BuyPlant -> {
                TODO()
            }
        }
    }
}