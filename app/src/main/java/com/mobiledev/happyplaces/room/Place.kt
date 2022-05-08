package com.mobiledev.happyplaces.room

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "PlaceTable")
data class Place(
    @ColumnInfo(name = "PlaceName")
    val placeName:String,
    @ColumnInfo(name = "PlaceImg")
    val img:Int,
    @ColumnInfo(name = "Location")
    val location:String
)
