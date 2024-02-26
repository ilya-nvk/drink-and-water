package com.ilyanvk.drinkwater.presentation.navigationbar

import com.ilyanvk.drinkwater.R

sealed class BottomNavigationItems(var route: String, var iconId: Int, var titleId: Int) {
    data object Shop :
        BottomNavigationItems("shop", R.drawable.outline_storefront_24, R.string.shop)

    data object Gallery :
        BottomNavigationItems("gallery", R.drawable.outline_local_florist_24, R.string.gallery)

    data object Tracker :
        BottomNavigationItems("tracker", R.drawable.outline_water_drop_24, R.string.tracker)

    data object Profile :
        BottomNavigationItems("profile", R.drawable.outline_person_24, R.string.profile)

    data object Settings :
        BottomNavigationItems("settings", R.drawable.outline_settings_24, R.string.settings)
}