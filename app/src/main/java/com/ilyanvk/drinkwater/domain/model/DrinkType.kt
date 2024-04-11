package com.ilyanvk.drinkwater.domain.model

enum class DrinkType(val coefficient: Double) {
    WATER(1.0),
    COFFEE(0.83),
    TEA(1.0),
    MILK(1.0),
    JUICE(0.86),
    SODA(0.88),
    BEER(0.9),
    WINE(0.85),
    CHAMPAIGN(0.8)
}
