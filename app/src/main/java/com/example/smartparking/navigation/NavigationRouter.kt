package com.example.smartparking.navigation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

object NavigationRouter {
    var currentScreen: MutableState<Screen> = mutableStateOf(Screen.LoginScreen)
}