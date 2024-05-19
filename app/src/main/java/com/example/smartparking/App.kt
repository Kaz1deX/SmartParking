package com.example.smartparking

import android.app.Application
import com.example.smartparking.data.repositories.MainRepository
import com.example.smartparking.data.room.dao.CarDao
import com.example.smartparking.data.room.database.MainDatabase

class App : Application() {

    val mainDatabase by lazy { MainDatabase.createDataBase(this) }

    val repository by lazy { MainRepository(mainDatabase.carDao(), applicationContext) }
//    val mainDatabase = MainDatabase.createDataBase(this)


//    lateinit var mainDatabase: MainDatabase
//
//    override fun onCreate() {
//        super.onCreate()
//        mainDatabase = MainDatabase.createDataBase(this)
//    }
}