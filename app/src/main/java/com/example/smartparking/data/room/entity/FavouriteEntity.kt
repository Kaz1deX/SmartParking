package com.example.smartparking.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourites_table")
data class FavouriteEntity(
    @PrimaryKey(autoGenerate = false) val parkingId: String,
    val userLogin: String
)