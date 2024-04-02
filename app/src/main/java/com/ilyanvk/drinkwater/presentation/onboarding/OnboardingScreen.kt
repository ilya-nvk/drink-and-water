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
        modifier = Modifier.fillMaxSize(),
    ) {
        NavHost(
            navController = navController,
            startDestination = OnboardingNavigationScreens.Screen1.route,
            modifier = Modifier
                .padding(it)
        ) {
            composable(OnboardingNavigationScreens.Screen1.route) {
                BaseOnboardingScreen(
                    picture = painterResource(id = R.drawable.lady_drinking),
                    title = stringResource(R.string.onboarding_welcome_title),
                    text = stringResource(R.string.onboarding_welcome_text),
                    button = {
                        Button(onClick = { navController.navigate(OnboardingNavigationScreens.Screen2.route) }) {
                            Text(stringResource(R.string.next))
                        }
                    }
                )
            }
            composable(OnboardingNavigationScreens.Screen2.route) {
                BaseOnboardingScreen(
                    picture = painterResource(id = R.drawable.nature),
                    title = stringResource(R.string.onboarding_gamification_title),
                    text = stringResource(R.string.onboarding_gamification_text),
                    button = {
                        Button(onClick = { navController.navigate(OnboardingNavigationScreens.Screen3.route) }) {
                            Text(stringResource(R.string.next))
                        }
                    }
                )
            }
            composable(OnboardingNavigationScreens.Screen3.route) {
                BaseOnboardingScreen(
                    picture = painterResource(id = R.drawable.notification_bell),
                    title = stringResource(R.string.onboarding_reminders_title),
                    text = stringResource(R.string.onboarding_reminders_text),
                    button = {
                        Button(onClick = { navController.navigate(OnboardingNavigationScreens.Screen4.route) }) {
                            Text(stringResource(R.string.next))
                        }
                    }
                )
            }
            composable(OnboardingNavigationScreens.Screen4.route) {
                ProfileRegistrationScreen(onButtonClick = {
                    navController.navigate(
                        OnboardingNavigationScreens.Screen5.route
                    )
                })
            }
            composable(OnboardingNavigationScreens.Screen5.route) {
                BaseOnboardingScreen(
                    picture = painterResource(id = R.drawable.coin_image),
                    title = stringResource(R.string.onboarding_coins_title),
                    text = stringResource(id = R.string.coins_description),
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
