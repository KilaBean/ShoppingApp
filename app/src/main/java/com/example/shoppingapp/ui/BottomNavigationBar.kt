package com.example.shoppingapp.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.shoppingapp.nav.Routes

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        Routes.HOME to Icons.Default.Home,
        Routes.CART to Icons.Default.ShoppingCart
    )

    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { (route, icon) ->
            NavigationBarItem(
                selected = currentRoute == route,
                onClick = { navController.navigate(route) },
                icon = { Icon(icon, contentDescription = null) },
                label = { Text(route.replaceFirstChar { it.uppercase() }) }
            )
        }
    }
}
