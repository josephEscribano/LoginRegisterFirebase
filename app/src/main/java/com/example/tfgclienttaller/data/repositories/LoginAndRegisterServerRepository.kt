package com.example.tfgclienttaller.data.repositories

import com.example.tfgclienttaller.data.sources.remotes.RemoteDataSource
import com.example.tfgclienttaller.domain.ApiRespuesta
import com.example.tfgclienttaller.domain.Usuario
import com.example.tfgclienttaller.utils.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

import javax.inject.Inject

class LoginAndRegisterServerRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) {
    suspend fun registerServer(usuario: Usuario) {
        remoteDataSource.registerServer(usuario)
    }

    suspend fun doLogin(usuario: Usuario) : Flow<NetworkResult<Usuario>>  {
        return flow {
            emit(remoteDataSource.doLogin(usuario))
        }.flowOn(Dispatchers.IO)
    }
}