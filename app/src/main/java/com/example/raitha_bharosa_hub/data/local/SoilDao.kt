package com.example.raitha_bharosa_hub.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SoilDao {

    @Insert
    suspend fun insertSoil(

        soil: SoilEntity
    )

    @Query(
        "SELECT * FROM soil_history ORDER BY id DESC"
    )
    suspend fun getAllHistory():

            List<SoilEntity>
}