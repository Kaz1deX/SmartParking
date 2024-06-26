package com.example.smartparking.data.api

import android.util.Log
import com.example.smartparking.data.model.Booking
import com.example.smartparking.data.model.BookingReceive
import com.example.smartparking.data.model.Car
import com.example.smartparking.data.model.CarReceive
import com.example.smartparking.data.model.LoginResponse
import com.example.smartparking.data.model.Parking
import com.example.smartparking.data.model.UserLogin
import com.example.smartparking.data.model.UserRegister
import com.example.smartparking.data.model.UserResponse
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.accept
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

interface ApiService {

    suspend fun register(userRegister: UserRegister): LoginResponse

    suspend fun auth(userLogin: UserLogin): LoginResponse

    suspend fun getUserBuLogin(login: String): UserResponse

    suspend fun getCars(login: String): List<Car>

    suspend fun addCar(car: CarReceive): Boolean

    suspend fun getParking(): List<Parking>

    suspend fun getParkingById(parkingId: String): Parking

    suspend fun deleteCar(login: String, number: String): Boolean

    suspend fun getBooking(login: String): List<Booking>

    suspend fun addBooking(bookingReceive: BookingReceive): Boolean

    suspend fun deleteBooking(bookingId: String): Boolean

    suspend fun getAvailableSlots(parkingId: String, date: String): List<Pair<String, String>>

    companion object {

        fun create(): ApiService {
            return ApiServiceImpl(
                client = HttpClient(CIO) {
                    // Logging
                    install(Logging) {
                        logger = object : Logger {
                            override fun log(message: String) {
                                Log.d("HTTP call", message)
                            }
                        }
                        level = LogLevel.ALL
                    }

                    // JSON
                    install(ContentNegotiation) {
                        json(Json {
                            prettyPrint = true
                            isLenient = true
                            encodeDefaults = false
                            ignoreUnknownKeys = true
                        })
                    }
                    // Timeout
                    install(HttpTimeout) {
                        requestTimeoutMillis = 15000L
                        connectTimeoutMillis = 15000L
                        socketTimeoutMillis = 15000L
                    }
                    // Apply to all requests
                    defaultRequest {
                        contentType(ContentType.Application.Json)
                        accept(ContentType.Application.Json)
                    }
                }
            )
        }
    }
}