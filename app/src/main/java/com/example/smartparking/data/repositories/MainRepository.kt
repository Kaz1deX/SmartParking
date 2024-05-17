package com.example.smartparking.data.repositories

import com.example.smartparking.data.api.ApiService
import com.example.smartparking.data.model.UserLogin
import com.example.smartparking.data.model.UserRegister

class MainRepository {
    private val apiService by lazy {
        ApiService.create()
    }

    suspend fun auth(login: String, password: String): String {
        val userLogin = UserLogin(login, password)
        val authResponse = apiService.auth(userLogin)
        return authResponse.token
    }

    suspend fun register(login: String, password: String, username: String, email: String): String {
        val userRegister = UserRegister(login, password, username)
        val registerResponse = apiService.register(userRegister)
        return registerResponse.token
    }
}