package com.example.tfgclienttaller.data.room.dao

import androidx.room.*
import com.example.tfgclienttaller.data.model.TokenEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WheelFixDao {
    @Transaction
    @Query("select token from savetoken where tokenid = 1")
    fun getToken() : Flow<String>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertToken(token:TokenEntity)

    @Update
    suspend fun updateToken(token: TokenEntity)

}