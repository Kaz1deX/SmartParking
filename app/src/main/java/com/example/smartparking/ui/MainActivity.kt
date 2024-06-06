package com.example.smartparking.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.smartparking.navigation.Navigation
import com.example.smartparking.navigation.NavigationRouter
import com.example.smartparking.navigation.Screen
import com.example.smartparking.ui.navbar.BottomNavigationBar
import com.example.smartparking.ui.theme.SmartParkingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            SmartParkingTheme {
                Scaffold(
                    bottomBar = {
                        when (NavigationRouter.currentScreen.value) {
                            Screen.LoginScreen,
                            Screen.RegistrationScreen,
                            Screen.CarsScreen,
                            Screen.AddCarScreen,
                            Screen.ChoiceParkingScreen -> null
                            else -> BottomNavigationBar(navController = navController)
                        }
                    }
                ) { contentPadding ->
                    run {
                        Box(modifier = Modifier.padding(contentPadding)) {
                            Navigation(navController, applicationContext)
                        }
                    }
                }
            }
        }
    }
}