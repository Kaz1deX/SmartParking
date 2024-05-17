package com.example.smartparking.data.model

import kotlinx.serialization.Serializable

@Serializable
data class UserRegister(
    val login: String = "",
    val email: String? = "",
    val password: String = "",
    val username: String = ""
)