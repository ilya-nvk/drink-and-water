package com.ilyanvk.drinkwater.presentation.onboarding

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ilyanvk.drinkwater.R
import com.ilyanvk.drinkwater.presentation.onboarding.navigation.OnboardingNavigationScreens

@Composable
fun OnboardingScreen(
    onFinished: () -> Unit
) {
    val navController = rememberNavController()
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        NavHost(
            navController = navController,
            startDestination = OnboardingNavigationScreens.Screen1.route,
            modifier = Modifier
                .padding(it)
        ) {
            composable(OnboardingNavigationScreens.Screen1.route) {
                BaseOnboardingScreen(
                    picture = painterResource(id = R.drawable.plant_test),
                    title = "Track your daily water intake with us",
                    text = "Achieve your hydration goals with a simple tap!",
                    button = {
                        Button(onClick = { navController.navigate(OnboardingNavigationScreens.Screen2.route) }) {
                            Text(stringResource(R.string.next))
                        }
                    }
                )
            }
            composable(OnboardingNavigationScreens.Screen2.route) {
                BaseOnboardingScreen(
                    picture = painterResource(id = R.drawable.plant_test),
                    title = "Keep motivation with plants growing gamification",
                    text = "Drink water and water the plants!\nHow many plants can you grow?",
                    button = {
                        Button(onClick = { navController.navigate(OnboardingNavigationScreens.Screen3.route) }) {
                            Text(stringResource(R.string.next))
                        }
                    }
                )
            }
            composable(OnboardingNavigationScreens.Screen3.route) {
                BaseOnboardingScreen(
                    picture = painterResource(id = R.drawable.plant_test),
                    title = "Custom remainders",
                    text = "Quick and easy to set your hydration goal and then track your daily water intake progress.",
                    button = {
                        Button(onClick = { onFinished() }) {
                            Text(stringResource(R.string.finish))
                        }
                    }
                )
            }
        }
    }
}
