package com.example.smartparking.navigation

import com.example.smartparking.R

sealed class Screen(val route: String, var icon: Int = R.drawable.ic_launcher_foreground, var title: String) {
    data object ProfileScreen: Screen(route = "profile_screen", icon = R.drawable.profile_icon, title = "Профиль")
    data object MapScreen: Screen(route = "map_screen", icon = R.drawable.map_icon, title = "Карта")
    data object FavouritesScreen: Screen(route = "favourites_screen", icon = R.drawable.favourites_icon, title = "Избранное")
    data object SettingsScreen: Screen(route = "settings_screen", icon = R.drawable.settings_icon, title = "Настройки")
    data object LoginScreen: Screen(route = "login_screen", title = "LoginScreen")
    data object RegistrationScreen: Screen(route = "registration_screen", title = "RegistrationScreen")
    data object CarsScreen: Screen(route = "cars_screen", title = "CarsScreen")
    data object AddCarScreen: Screen(route = "add_car_screen", title = "AddCarScreen")
}