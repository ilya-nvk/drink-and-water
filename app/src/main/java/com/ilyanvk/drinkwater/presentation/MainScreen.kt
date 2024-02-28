package com.ilyanvk.drinkwater.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ilyanvk.drinkwater.presentation.navigationbar.BottomNavigationBar
import com.ilyanvk.drinkwater.presentation.navigationbar.BottomNavigationItems
import com.ilyanvk.drinkwater.presentation.profile.ProfileScreen
import com.ilyanvk.drinkwater.presentation.tracker.TrackerScreen
import com.ilyanvk.drinkwater.ui.theme.DrinkWaterTheme

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        NavHost(
            navController = navController,
            startDestination = BottomNavigationItems.Tracker.route,
            modifier = Modifier.weight(1f)
        ) {
            composable(BottomNavigationItems.Shop.route) {
                Text(text = "1")
            }
            composable(BottomNavigationItems.Gallery.route) {
                Text(text = "2")
            }
            composable(BottomNavigationItems.Tracker.route) {
                TrackerScreen(modifier = Modifier.fillMaxSize())
            }
            composable(BottomNavigationItems.Profile.route) {
                ProfileScreen(modifier = Modifier.fillMaxSize())
            }
            composable(BottomNavigationItems.Settings.route) {
                Text(text = "5")
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