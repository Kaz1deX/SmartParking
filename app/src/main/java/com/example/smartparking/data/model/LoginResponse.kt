package com.example.smartparking.data.model

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    val rowId: String,
    val login: String,
    val token: String
)
