package com.example.smartparking.data.model

import kotlinx.serialization.Serializable

@Serializable
data class CarReceive(
    val userLogin: String,
    val number: String,
    val model: String
)