package com.mobiledev.happyplaces.fragments.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.mobiledev.happyplaces.model.Place
import com.mobiledev.happyplaces.repository.PlaceRepository
import com.mobiledev.happyplaces.room.PlaceDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(application: Application): AndroidViewModel(application) {
     val allPlaces:LiveData<List<Place>>
     private val repository:PlaceRepository
    init {
        val dao = PlaceDatabase.getDatabase(application).getPlaceDao()
        repository = PlaceRepository(dao)
        allPlaces = repository.allPlaces
    }
    fun deletePlace(place:Place) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(place)
    }
    fun updatePlace(place:Place) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(place)
    }
    fun addPlace(place:Place) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(place)
    }
}