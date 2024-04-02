package com.ilyanvk.drinkwater.domain.model

enum class DrinkType(val coefficient: Double) {
    WATER(1.0),
    TEA(0.8),
    COFFEE(0.7),
    SODA(0.5),
    JUICE(0.9),
    MILK(0.95),
    ALCOHOL(-0.3)
}
