package com.example.smartparking.data.model

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    val token: String
)
