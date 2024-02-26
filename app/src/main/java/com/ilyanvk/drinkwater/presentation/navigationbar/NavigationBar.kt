package com.ilyanvk.drinkwater.presentation.navigationbar

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState


@Composable
fun BottomNavigationBar(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val items = listOf(
        BottomNavigationItems.Shop,
        BottomNavigationItems.Gallery,
        BottomNavigationItems.Tracker,
        BottomNavigationItems.Profile,
        BottomNavigationItems.Settings
    )

    NavigationBar(modifier = modifier) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        Spacer(modifier = Modifier.width(8.dp))
        items.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        painter = painterResource(id = item.iconId),
                        contentDescription = stringResource(id = item.titleId),
                    )
                },
                label = {
//                    Text(
//                        stringResource(id = item.titleId),
//                        style = MaterialTheme.typography.labelMedium,
//                        modifier = Modifier.wrapContentWidth()
//                    )
                })
            Spacer(modifier = Modifier.width(8.dp))
        }
    }
}