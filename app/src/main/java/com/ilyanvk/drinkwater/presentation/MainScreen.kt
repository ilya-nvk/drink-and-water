package com.ilyanvk.drinkwater.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ilyanvk.drinkwater.presentation.gallery.GalleryScreen
import com.ilyanvk.drinkwater.presentation.navigationbar.BottomNavigationBar
import com.ilyanvk.drinkwater.presentation.navigationbar.BottomNavigationItems
import com.ilyanvk.drinkwater.presentation.profile.ProfileScreen
import com.ilyanvk.drinkwater.presentation.settings.SettingsScreen
import com.ilyanvk.drinkwater.presentation.shop.ShopScreen
import com.ilyanvk.drinkwater.presentation.tracker.TrackerScreen
import com.ilyanvk.drinkwater.ui.theme.DrinkWaterTheme

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        NavHost(
            navController = navController,
            startDestination = BottomNavigationItems.Tracker.route,
            modifier = Modifier
                .weight(1f)
                .background(MaterialTheme.colorScheme.background)
        ) {
            composable(BottomNavigationItems.Shop.route) {
                ShopScreen()
            }
            composable(BottomNavigationItems.Gallery.route) {
                GalleryScreen()
            }
            composable(BottomNavigationItems.Tracker.route) {
                TrackerScreen()
            }
            composable(BottomNavigationItems.Profile.route) {
                ProfileScreen()
            }
            composable(BottomNavigationItems.Settings.route) {
                SettingsScreen()
            }
        }

        BottomNavigationBar(navController)
    }
}


@Preview
@Composable
fun MainScreenPreview() {
    DrinkWaterTheme {
        MainScreen()
    }
}