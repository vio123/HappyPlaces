package com.mobiledev.happyplaces.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mobiledev.happyplaces.model.Place

@Database(entities = [Place::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class PlaceDatabase:RoomDatabase() {
    abstract fun getPlaceDao():PlaceDao
    companion object{
        @Volatile
        private var INSTANCE:PlaceDatabase ?= null

        fun getDatabase(context: Context):PlaceDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PlaceDatabase::class.java,
                    "place_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}