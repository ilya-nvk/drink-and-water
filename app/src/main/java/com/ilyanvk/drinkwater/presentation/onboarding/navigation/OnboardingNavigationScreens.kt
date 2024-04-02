package com.ilyanvk.drinkwater.presentation.onboarding.navigation

sealed class OnboardingNavigationScreens(val route: String) {
    data object Screen1 : OnboardingNavigationScreens("1")
    data object Screen2 : OnboardingNavigationScreens("2")
    data object Screen3 : OnboardingNavigationScreens("3")
    data object Screen4 : OnboardingNavigationScreens("4")
    data object Screen5 : OnboardingNavigationScreens("5")
}
