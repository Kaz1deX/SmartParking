package com.example.smartparking.data.model

data class Booking(
    val id: Int,
    val userLogin: String,
    val parkingId: Int,
    val carId: Int,
    val checkIn: String,
    val exit: String?,
    val amount: Double,
    val paymentStatus: Boolean,
    val numberOfPlace: Int
)

