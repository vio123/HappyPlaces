package com.mobiledev.happyplaces.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
@Dao
interface PlaceDao {
    @Query("SELECT * FROM PlaceTable")
    fun getAll(): List<Place>
    @Insert
    fun insertAll(place: Place)
    @Delete
    fun delete(place: Place)
}