package com.example.smartparking

import android.app.Application
import com.example.smartparking.data.repositories.MainRepository
import com.example.smartparking.data.room.database.MainDatabase

class App : Application() {
    val repository by lazy { MainRepository() }

    val mainDatabase by lazy { MainDatabase.createDataBase(this) }
//    val mainDatabase = MainDatabase.createDataBase(this)


//    lateinit var mainDatabase: MainDatabase
//
//    override fun onCreate() {
//        super.onCreate()
//        mainDatabase = MainDatabase.createDataBase(this)
//    }
}