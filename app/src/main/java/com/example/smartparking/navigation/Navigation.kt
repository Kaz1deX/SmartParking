package com.example.smartparking.navigation

import android.app.Activity
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.smartparking.App
import com.example.smartparking.ui.screen.cars.AddCarScreen
import com.example.smartparking.ui.screen.cars.CarsScreen
import com.example.smartparking.ui.screen.favourites.FavouritesScreen
import com.example.smartparking.ui.screen.login.LoginScreen
import com.example.smartparking.ui.screen.login.LoginViewModel
import com.example.smartparking.ui.screen.map.ChoiceParkingScreen
import com.example.smartparking.ui.screen.map.MapScreen
import com.example.smartparking.ui.screen.profile.ProfileScreen
import com.example.smartparking.park.ui.screen.registration.RegistrationScreen
import com.example.smartparking.ui.screen.profile.BookingScreen
import com.example.smartparking.ui.screen.settings.SettingsScreen

@Composable
fun Navigation(navController: NavHostController, context: Context) {

    val activity = LocalContext.current as Activity
    val application = activity.application as App
    val repository = application.repository

    val viewModel: LoginViewModel =
        viewModel(factory = LoginViewModel.LoginViewModelFactory(application, repository))

    val startDestination = if (viewModel.checkToken()) {
        Screen.MapScreen.route
    } else {
        Screen.LoginScreen.route
    }

    NavHost(navController = navController, startDestination = startDestination) {
        composable(route = Screen.ProfileScreen.route) {
            ProfileScreen(navController = navController, context = context)
        }

        composable(route = Screen.MapScreen.route) {
            NavigationRouter.currentScreen.value = Screen.MapScreen
            MapScreen(navController = navController, context = context)
        }

        composable(route = Screen.FavouritesScreen.route) {
            NavigationRouter.currentScreen.value = Screen.FavouritesScreen
            FavouritesScreen(navController = navController, context = context)
        }

        composable(route = Screen.SettingsScreen.route) {
            NavigationRouter.currentScreen.value = Screen.SettingsScreen
            SettingsScreen(navController = navController, context = context)
        }

        composable(route = Screen.LoginScreen.route) {
            NavigationRouter.currentScreen.value = Screen.LoginScreen
            LoginScreen(navController = navController, context = context)
        }

        composable(route = Screen.RegistrationScreen.route) {
            NavigationRouter.currentScreen.value = Screen.RegistrationScreen
            RegistrationScreen(navController = navController, context = context)
        }

        composable(route = Screen.CarsScreen.route) {
            NavigationRouter.currentScreen.value = Screen.CarsScreen
            CarsScreen(navController = navController, context = context)
        }

        composable(route = Screen.AddCarScreen.route) {
            NavigationRouter.currentScreen.value = Screen.AddCarScreen
            AddCarScreen(navController = navController, context = context)
        }

        composable(route = Screen.ChoiceParkingScreen.route) {
            NavigationRouter.currentScreen.value = Screen.ChoiceParkingScreen
            ChoiceParkingScreen(navController = navController, context = context)
        }

        composable(route = Screen.BookingScreen.route) {
            NavigationRouter.currentScreen.value = Screen.BookingScreen
            BookingScreen(navController = navController, context = context)
        }
    }
}