package com.example.smartparking.data.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.smartparking.data.room.entity.CarEntity

@Dao
interface CarDao {
    @Query("SELECT * FROM cars_table")
    suspend fun getAllCars(): List<CarEntity>

    @Query("SELECT COUNT(*) FROM cars_table")
    suspend fun getCarsCount(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCar(carEntity: CarEntity)

    @Delete
    suspend fun deleteCar(carEntity: CarEntity)
}