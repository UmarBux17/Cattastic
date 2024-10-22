package com.cat.cattasticapp

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PoiDao {
    @Insert
    suspend fun insert(poi: PointOfInterest)

    @Query("SELECT * FROM poi_table")
    suspend fun getAllPOIs(): List<PointOfInterest>

    @Query("DELETE FROM poi_table")
    suspend fun clearAllPOIs()
}
