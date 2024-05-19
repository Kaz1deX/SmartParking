package com.example.smartparking.data.repositories

import android.content.Context
import com.example.smartparking.data.api.ApiService
import com.example.smartparking.data.model.Car
import com.example.smartparking.data.model.CarReceive
import com.example.smartparking.data.model.Parking
import com.example.smartparking.data.model.UserLogin
import com.example.smartparking.data.model.UserRegister
import com.example.smartparking.data.model.toCar
import com.example.smartparking.data.model.toCarEntity
import com.example.smartparking.data.room.dao.CarDao
import com.example.smartparking.data.room.dao.FavouriteDao
import com.example.smartparking.util.isOnline

class MainRepository(
    private val carDao: CarDao,
    private val favouriteDao: FavouriteDao,
    private val context: Context
) {
    private val apiService by lazy {
        ApiService.create()
    }

    suspend fun auth(login: String, password: String): String {
        if (isOnline(context)) {
            val userLogin = UserLogin(login, password)
            val authResponse = apiService.auth(userLogin)
            return authResponse.token
        } else {
            return "No internet connection"
        }
    }

    suspend fun register(login: String, password: String, username: String, email: String): String {
        if (isOnline(context)) {
            val userRegister = UserRegister(login, email, password, username)
            val registerResponse = apiService.register(userRegister)
            return registerResponse.token
        } else {
            return "No internet connection"
        }
    }

    suspend fun getCars(login: String): List<Car> {
        if (isOnline(context)) {
            val cars = apiService.getCars(login)
            if (cars.isNotEmpty()) {
                cars.forEach {
                    val carEntity = it.toCarEntity()
                    carDao.insertCar(carEntity)
                }
            }
            return cars
        } else {
            val cars = carDao.getAllCars().map { it.toCar() }
            return cars
        }
    }

    suspend fun addCar(car: CarReceive): Boolean {
        val addCar = apiService.addCar(car)
        if (addCar) {
            val carEntity = car.toCarEntity()
            carDao.insertCar(carEntity)
        }
        return addCar
    }

    suspend fun deleteCarsTable() {
        carDao.deleteCarsTable()
    }

    suspend fun deleteFavouriteTable() {
        favouriteDao.deleteFavouriteTable()
    }

    suspend fun getParking(): List<Parking> {
        if (isOnline(context)) {
            val parking = apiService.getParking()
            return parking
        } else {
            return emptyList()
        }
    }
}
