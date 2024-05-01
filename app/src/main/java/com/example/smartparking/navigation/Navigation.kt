package com.example.smartparking.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.smartparking.ui.screen.favourites.FavouritesScreen
import com.example.smartparking.ui.screen.login.LoginScreen
import com.example.smartparking.ui.screen.map.MapScreen
import com.example.smartparking.ui.screen.profile.ProfileScreen
import com.example.smartparking.ui.screen.registration.RegistrationScreen
import com.example.smartparking.ui.screen.settings.SettingsScreen

@Composable
fun Navigation(navController: NavHostController, context: Context) {
    NavHost(navController = navController, startDestination = Screen.MapScreen.route) {
        composable(route = Screen.ProfileScreen.route) {
            ProfileScreen(navController = navController, context = context)
        }

        composable(route = Screen.MapScreen.route) {
            MapScreen(navController = navController, context = context)
        }

        composable(route = Screen.FavouritesScreen.route) {
            FavouritesScreen(navController = navController, context = context)
        }

        composable(route = Screen.SettingsScreen.route) {
            SettingsScreen(navController = navController, context = context)
        }

        composable(route = Screen.LoginScreen.route) {
            LoginScreen(navController = navController, context = context)
        }

        composable(route = Screen.RegistrationScreen.route) {
            RegistrationScreen(navController = navController, context = context)
        }
    }
}