package com.example.todiary.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todiary.ui.main.MainScreen
import com.example.todiary.ui.profile.ProfileScreen
import com.example.todiary.ui.theme.TodiaryTheme

@Composable
fun TodiaryApp() {
    TodiaryTheme {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = Destinations.Main.route) {
            composable(Destinations.Main.route) {
                MainScreen()
            }
            composable(Destinations.Profile.route) {
                ProfileScreen()
            }
        }
    }
}

sealed class Destinations(
    val route: String
) {
    object Main : Destinations("main")
    object Profile : Destinations("profile")
}