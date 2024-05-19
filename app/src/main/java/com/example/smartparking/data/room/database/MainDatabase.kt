package com.example.smartparking.data.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.smartparking.data.room.dao.CarDao
import com.example.smartparking.data.room.dao.FavouriteDao
import com.example.smartparking.data.room.entity.CarEntity
import com.example.smartparking.data.room.entity.FavouriteEntity

@Database(entities = [CarEntity::class, FavouriteEntity::class], version = 1)
abstract class MainDatabase : RoomDatabase() {

    abstract fun carDao(): CarDao
    abstract fun favouriteDao(): FavouriteDao

    companion object {
        fun createDataBase(context: Context): MainDatabase {
            return Room.databaseBuilder(
                context,
                MainDatabase::class.java,
                "main_db"
            ).build()
        }
    }
}