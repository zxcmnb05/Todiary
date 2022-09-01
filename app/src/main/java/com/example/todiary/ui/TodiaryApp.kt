package com.example.todiary.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.todiary.ui.theme.TodiaryTheme

@Composable
fun TodiaryApp() {
    TodiaryTheme {
        val navController = rememberNavController()
        NavHost(navController, startDestination = NavigationKeys.Route.MAIN) {

        }
    }
}

object NavigationKeys {
    object Route{
        const val PROFILE = "profile"
        const val MAIN = "main"
    }
}