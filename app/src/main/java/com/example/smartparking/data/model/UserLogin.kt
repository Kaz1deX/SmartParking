package com.example.smartparking.data.model

import kotlinx.serialization.Serializable

@Serializable
data class UserLogin(
    val login: String = "",
    val password: String = ""
)
