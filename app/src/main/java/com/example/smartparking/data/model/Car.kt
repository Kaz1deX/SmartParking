package com.example.smartparking.data.model

import com.example.smartparking.data.room.entity.CarEntity
import kotlinx.serialization.Serializable

@Serializable
data class Car(
    val number: String,
    val model: String
)

fun Car.toCarEntity(): CarEntity {
    return CarEntity(
        number = number,
        model = model
    )
}

fun CarEntity.toCar(): Car {
    return Car(
        number = number,
        model = model
    )
}
