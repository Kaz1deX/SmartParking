package com.example.smartparking.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cars_table")
data class CarEntity(
    @PrimaryKey(autoGenerate = false) val id: Int,
    val userLogin: String,
    val number: String,
    val model: String
)
//{
//    fun convertToFieldModel() = FieldModel(car, "")
//}