package com.mobiledev.happyplaces.repository

import androidx.lifecycle.LiveData
import com.mobiledev.happyplaces.model.Place
import com.mobiledev.happyplaces.room.PlaceDao

class PlaceRepository(private val placeDao: PlaceDao) {
    val allPlaces:LiveData<List<Place>> = placeDao.getAllPlace()
    suspend fun insert(place:Place){
        placeDao.insert(place)
    }
    suspend fun delete(place:Place){
        placeDao.delete(place)
    }
     suspend fun update(place: Place){
        placeDao.update(place)
    }
}