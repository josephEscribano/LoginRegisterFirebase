package com.example.tfgclienttaller.data.repositories

import com.example.tfgclienttaller.data.model.toTokenEntity
import com.example.tfgclienttaller.data.sources.local.LocalDataSource
import com.example.tfgclienttaller.domain.MyToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LocalRepository@Inject constructor(
    private val localDataSource: LocalDataSource
) {

    fun getToken() : Flow<String> {
        return localDataSource.getToken().flowOn(Dispatchers.IO)
    }

    fun insertToken(token: MyToken)  {
        localDataSource.insertToken(token.toTokenEntity())
    }
}