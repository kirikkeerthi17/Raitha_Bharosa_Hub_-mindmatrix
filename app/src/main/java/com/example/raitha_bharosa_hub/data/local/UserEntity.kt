package com.example.raitha_bharosa_hub.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity(

    @PrimaryKey
    val id: Int = 1,

    val name: String,

    val crop: String,

    val language: String
)