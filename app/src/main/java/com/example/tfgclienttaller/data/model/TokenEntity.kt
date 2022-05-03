package com.example.tfgclienttaller.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "savetoken")
data class TokenEntity(
    @PrimaryKey(autoGenerate = true)
    var tokenid : Int,
    var token : String
)
