package com.example.smartparking.data.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.smartparking.data.room.entity.FavouriteEntity

@Dao
interface FavouriteDao {
    @Query("SELECT * FROM favourites_table")
    suspend fun getAllFavourites(): List<FavouriteEntity>

    @Query("SELECT COUNT(*) FROM favourites_table")
    suspend fun getFavouritesCount(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFavourite(favouriteEntity: FavouriteEntity)

    @Delete
    suspend fun deleteFavourite(favouriteEntity: FavouriteEntity)

    @Query("DELETE FROM favourites_table")
    suspend fun deleteFavouriteTable()
}