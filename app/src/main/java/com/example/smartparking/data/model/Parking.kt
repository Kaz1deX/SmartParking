package com.example.smartparking.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Parking(
    val id: String,
    val name: String,
    val image: String,
    val address: String,
    val location: String,
    val description: String,
    val totalPlaces: Int,
    val availablePlaces: Int,
    val costPerHour: Int,
    val chargingStation: Boolean,
    val opened: String,
    val closed: String,
)