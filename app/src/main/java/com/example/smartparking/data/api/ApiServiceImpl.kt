package com.example.smartparking.data.api

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
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import io.ktor.http.isSuccess

class ApiServiceImpl(
    private val client: HttpClient
) : ApiService {
    override suspend fun register(userRegister: UserRegister): LoginResponse {
        try {
            val response: HttpResponse = client.post {
                url(ApiRoutes.BASE_URL + ApiRoutes.REGISTRATION)
                setBody(userRegister)
            }
            return response.body()

        } catch (ex: RedirectResponseException) {
            throw Exception("Redirect error: ${ex.response.status.description}")
        } catch (ex: ClientRequestException) {
            throw Exception("Client request error: ${ex.response.status.description}")
        } catch (ex: ServerResponseException) {
            throw Exception("Server response error: ${ex.response.status.description}")
        }
    }

    override suspend fun auth(userLogin: UserLogin): LoginResponse {
        try {
            val response: HttpResponse = client.post {
                url(ApiRoutes.BASE_URL + ApiRoutes.AUTHENTICATE)
                setBody(userLogin)
            }
            return response.body()

        } catch (ex: RedirectResponseException) {
            throw Exception("Redirect error: ${ex.response.status.description}")
        } catch (ex: ClientRequestException) {
            throw Exception("Client request error: ${ex.response.status.description}")
        } catch (ex: ServerResponseException) {
            throw Exception("Server response error: ${ex.response.status.description}")
        }
    }

    override suspend fun getUserBuLogin(login: String): UserResponse {
        try {
            val response: HttpResponse = client.get {
                url(ApiRoutes.BASE_URL + ApiRoutes.USER)
                parameter("login", login)
            }
            return response.body()

        } catch (ex: RedirectResponseException) {
            throw Exception("Redirect error: ${ex.response.status.description}")
        } catch (ex: ClientRequestException) {
            throw Exception("Client request error: ${ex.response.status.description}")
        } catch (ex: ServerResponseException) {
            throw Exception("Server response error: ${ex.response.status.description}")
        }
    }

    override suspend fun getCars(login: String): List<Car> {
        try {
            val response: HttpResponse = client.get {
                url(ApiRoutes.BASE_URL + ApiRoutes.CARS)
                parameter("login", login)
            }
            return response.body()

        } catch (ex: RedirectResponseException) {
            throw Exception("Redirect error: ${ex.response.status.description}")
        } catch (ex: ClientRequestException) {
            throw Exception("Client request error: ${ex.response.status.description}")
        } catch (ex: ServerResponseException) {
            throw Exception("Server response error: ${ex.response.status.description}")
        }
    }

    override suspend fun addCar(car: CarReceive): Boolean {
        try {
            val response: HttpResponse = client.post {
                url(ApiRoutes.BASE_URL + ApiRoutes.ADD_CAR)
                setBody(car)
            }

            if (response.status.isSuccess()) return true
            else {
                return false
                throw Exception("Request failed with status: ${response.status.value}")
            }

        } catch (ex: RedirectResponseException) {
            throw Exception("Redirect error: ${ex.response.status.description}")
        } catch (ex: ClientRequestException) {
            throw Exception("Client request error: ${ex.response.status.description}")
        } catch (ex: ServerResponseException) {
            throw Exception("Server response error: ${ex.response.status.description}")
        }
    }

    override suspend fun getParking(): List<Parking> {
        try {
            val response: HttpResponse = client.get {
                url(ApiRoutes.BASE_URL + ApiRoutes.ALL_PARKING)
            }
            return response.body()

        } catch (ex: RedirectResponseException) {
            throw Exception("Redirect error: ${ex.response.status.description}")
        } catch (ex: ClientRequestException) {
            throw Exception("Client request error: ${ex.response.status.description}")
        } catch (ex: ServerResponseException) {
            throw Exception("Server response error: ${ex.response.status.description}")
        }
    }

    override suspend fun getParkingById(parkingId: String): Parking {
        try {
            val response: HttpResponse = client.get {
                url(ApiRoutes.BASE_URL + ApiRoutes.PARKING)
                parameter("parkingId", parkingId)
            }
            return response.body()

        } catch (ex: RedirectResponseException) {
            throw Exception("Redirect error: ${ex.response.status.description}")
        } catch (ex: ClientRequestException) {
            throw Exception("Client request error: ${ex.response.status.description}")
        } catch (ex: ServerResponseException) {
            throw Exception("Server response error: ${ex.response.status.description}")
        }
    }

    override suspend fun deleteCar(login: String, number: String): Boolean {
        try {
            val response: HttpResponse = client.delete {
                url(ApiRoutes.BASE_URL + ApiRoutes.DELETE_CAR)
                parameter("login", login)
                parameter("number", number)
            }

            if (response.status.isSuccess()) return true
            else {
                return false
                throw Exception("Request failed with status: ${response.status.value}")
            }

        } catch (ex: RedirectResponseException) {
            throw Exception("Redirect error: ${ex.response.status.description}")
        } catch (ex: ClientRequestException) {
            throw Exception("Client request error: ${ex.response.status.description}")
        } catch (ex: ServerResponseException) {
            throw Exception("Server response error: ${ex.response.status.description}")
        }
    }

    override suspend fun getBooking(login: String): List<Booking> {
        try {
            val response: HttpResponse = client.get {
                url(ApiRoutes.BASE_URL + ApiRoutes.BOOKING)
                parameter("login", login)
            }
            return response.body()

        } catch (ex: RedirectResponseException) {
            throw Exception("Redirect error: ${ex.response.status.description}")
        } catch (ex: ClientRequestException) {
            throw Exception("Client request error: ${ex.response.status.description}")
        } catch (ex: ServerResponseException) {
            throw Exception("Server response error: ${ex.response.status.description}")
        }
    }

    override suspend fun addBooking(booking: BookingReceive): Boolean {
        try {
            val response: HttpResponse = client.post {
                url(ApiRoutes.BASE_URL + ApiRoutes.ADD_BOOKING)
                setBody(booking)
            }

            if (response.status.isSuccess()) return true
            else {
                return false
                throw Exception("Request failed with status: ${response.status.value}")
            }

        } catch (ex: RedirectResponseException) {
            throw Exception("Redirect error: ${ex.response.status.description}")
        } catch (ex: ClientRequestException) {
            throw Exception("Client request error: ${ex.response.status.description}")
        } catch (ex: ServerResponseException) {
            throw Exception("Server response error: ${ex.response.status.description}")
        }
    }

    override suspend fun deleteBooking(bookingId: String): Boolean {
        try {
            val response: HttpResponse = client.delete {
                url(ApiRoutes.BASE_URL + ApiRoutes.BOOKING)
                parameter("bookingId", bookingId)
            }

            if (response.status.isSuccess()) return true
            else {
                return false
                throw Exception("Request failed with status: ${response.status.value}")
            }

        } catch (ex: RedirectResponseException) {
            throw Exception("Redirect error: ${ex.response.status.description}")
        } catch (ex: ClientRequestException) {
            throw Exception("Client request error: ${ex.response.status.description}")
        } catch (ex: ServerResponseException) {
            throw Exception("Server response error: ${ex.response.status.description}")
        }
    }

    override suspend fun getAvailableSlots(
        parkingId: String,
        date: String
    ): List<Pair<String, String>> {
        try {
            val response: HttpResponse = client.get {
                url(ApiRoutes.BASE_URL + ApiRoutes.AVAILABLE_SLOTS)
                parameter("parkingId", parkingId)
                parameter("date", date)
            }
            return response.body()

        } catch (ex: RedirectResponseException) {
            throw Exception("Redirect error: ${ex.response.status.description}")
        } catch (ex: ClientRequestException) {
            throw Exception("Client request error: ${ex.response.status.description}")
        } catch (ex: ServerResponseException) {
            throw Exception("Server response error: ${ex.response.status.description}")
        }
    }
}