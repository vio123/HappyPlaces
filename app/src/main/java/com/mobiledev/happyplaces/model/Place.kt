package com.mobiledev.happyplaces.model

import android.graphics.Bitmap
import androidx.room.*

@Entity(tableName = "PlaceTable")
data class Place(
    @ColumnInfo(name = "PlaceName")
    val placeName: String,
    @ColumnInfo(name = "PlaceImg")
    val img: Bitmap,
    @ColumnInfo(name = "DateTime")
    val dateTime: String,
    @ColumnInfo(name = "ActivityType")
    val activityType:String,
    @ColumnInfo(name = "PlaceType")
    val placeType:String,
    @ColumnInfo(name = "Adress")
    val adress:String,
    @ColumnInfo(name = "Latitude")
    val latitude:Double,
    @ColumnInfo(name = "Longitude")
    val longitude:Double,
    @ColumnInfo(name = "Note")
    val note:String,
    @PrimaryKey(autoGenerate = true)
    var id: Int=0,
    @ColumnInfo(name = "Favourite")
    var fav:Boolean
){
}
