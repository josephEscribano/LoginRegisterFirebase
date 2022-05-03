package com.example.tfgclienttaller.data.sources.local

import com.example.tfgclienttaller.data.model.TokenEntity
import com.example.tfgclienttaller.data.room.dao.WheelFixDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val wheelFixDao: WheelFixDao
) {
    fun getToken() : Flow<String> = wheelFixDao.getToken()

    fun insertToken(tokenEntity: TokenEntity) = wheelFixDao.insertToken(tokenEntity)

}