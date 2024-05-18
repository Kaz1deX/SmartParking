package com.example.smartparking.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Car(
    val number: String,
    val model: String
)

