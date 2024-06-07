package com.example.smartparking.data.model

import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    val login: String,
    val password: String,
    val username: String,
    val email: String?
)
