package com.cat.cattasticapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [PointOfInterest::class], version = 1)
abstract class PoiDatabase : RoomDatabase() {
    abstract fun poiDao(): PoiDao

    companion object {
        @Volatile
        private var INSTANCE: PoiDatabase? = null

        fun getDatabase(context: Context): PoiDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PoiDatabase::class.java,
                    "poi_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
