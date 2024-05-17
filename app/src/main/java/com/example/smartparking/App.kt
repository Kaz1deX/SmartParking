package com.example.smartparking

import android.app.Application
import com.example.smartparking.data.repositories.MainRepository

class App : Application() {
    val repository by lazy { MainRepository() }
}