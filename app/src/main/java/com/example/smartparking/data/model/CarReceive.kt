package com.example.smartparking.data.model

import com.example.smartparking.data.room.entity.CarEntity
import kotlinx.serialization.Serializable

@Serializable
data class CarReceive(
    val userLogin: String,
    val number: String,
    val model: String
)

fun CarReceive.toCarEntity(): CarEntity {
    return CarEntity(
        number = number,
        model = model
    )
}
