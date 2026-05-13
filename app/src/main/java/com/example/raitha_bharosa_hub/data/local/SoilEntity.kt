package com.example.raitha_bharosa_hub.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "soil_history")
data class SoilEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val nitrogen: String,

    val phosphorus: String,

    val potassium: String,

    val result: String,

    val crop: String,

    val fertilizer: String
)