package com.example.smartparking.data.model

import kotlinx.serialization.Serializable

@Serializable
data class BookingReceive(
    val userLogin: String,
    val parkingId: String,
    val carNumber: String,
    val checkIn: String,
    val exit: String,
    val amount: Int
)
