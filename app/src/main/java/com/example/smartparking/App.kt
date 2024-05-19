package com.example.smartparking

import android.app.Application
import com.example.smartparking.data.repositories.MainRepository
import com.example.smartparking.data.room.database.MainDatabase

class App : Application() {

    val mainDatabase by lazy { MainDatabase.createDataBase(this) }

    val repository by lazy {
        MainRepository(
            mainDatabase.carDao(),
            mainDatabase.favouriteDao(),
            applicationContext
        )
    }
}