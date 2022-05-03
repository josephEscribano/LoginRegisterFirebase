package com.example.tfgclienttaller.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tfgclienttaller.data.model.TokenEntity
import com.example.tfgclienttaller.data.room.dao.WheelFixDao

@Database(
    entities = [TokenEntity::class],
    version = 1,
    exportSchema = true
)
abstract class WheelFixRoomDatabase : RoomDatabase(){
    abstract fun wheelFixDao() : WheelFixDao
}