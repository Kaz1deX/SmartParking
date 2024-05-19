package com.example.smartparking.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Parking(
    val id: Int,
    val name: String? = null,
    val image: String? = null,
    val address: String,
    val location: String,
    val description: String,
    val totalPlaces: Int,
    val availablePlaces: Int,
    val costPerHour: Int,
    val chargingStation: Boolean
)

