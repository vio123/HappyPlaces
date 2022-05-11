package com.mobiledev.happyplaces.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.mobiledev.happyplaces.model.Place

@Dao
interface PlaceDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(place:Place)
    @Update
    suspend fun update(place:Place)
    @Delete
    suspend fun delete(place:Place)
    @Query("SELECT * FROM PlaceTable ORDER BY id ASC")
    fun getAllPlace():LiveData<List<Place>>

}