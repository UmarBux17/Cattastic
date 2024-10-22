package com.cat.cattasticapp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "poi_table")
data class PointOfInterest(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val latitude: Double,
    val longitude: Double,
    val title: String,
    val description: String
)
