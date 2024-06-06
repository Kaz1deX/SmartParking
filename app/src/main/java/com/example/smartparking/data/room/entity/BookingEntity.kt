package com.example.smartparking.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "booking_table")
data class BookingEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
)