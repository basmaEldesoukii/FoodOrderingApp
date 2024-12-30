package com.basma.presentation.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun AppNavigationBar(navController: NavController) {

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    NavigationBar {
        val navItems = listOf(
            Screen.Tables,
            Screen.Menu,
            Screen.Settings,
            Screen.Orders
        )
        navItems.forEach { screen ->
            NavigationBarItem(
                selected = currentRoute == screen.route,
                onClick = {
                    if (currentRoute != screen.route) {
                        navController.navigate(screen.route) {
                            launchSingleTop = true
                            restoreState = true
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                        }
                    }
                },
                icon = { Icon(screen.icon, contentDescription = screen.label) },
                label = { Text(screen.label) }
            )
        }
    }
}

sealed class Screen(val route: String, val label: String, val icon: ImageVector) {
    object Tables : Screen(route = "tables", label = "Tables", icon = Icons.Default.Home)
    object Orders :
        Screen(route = "orders", label = "Orders", icon = Icons.Default.ShoppingCart)

    object Menu : Screen(route = "menu", label = "Menu", icon = Icons.Default.Menu)
    object Settings : Screen(route = "settings", label = "Settings", icon = Icons.Default.Settings)

}
