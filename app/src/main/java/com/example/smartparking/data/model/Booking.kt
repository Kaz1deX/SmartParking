package com.example.smartparking.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Booking(
    val id: String,
    val userLogin: String,
    val parkingId: String,
    val carNumber: Int,
    val checkIn: String,
    val exit: String?,
    val amount: Int,
    val paymentStatus: Boolean,
    val numberOfPlace: Int,
    val parkingName: String
)