package com.mobiledev.happyplaces.fragments.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mobiledev.happyplaces.room.Place

class HomeViewModel : ViewModel() {
    private var listData:MutableLiveData<Place> = MutableLiveData()
}