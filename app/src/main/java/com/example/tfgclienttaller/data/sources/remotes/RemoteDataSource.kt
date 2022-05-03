package com.example.tfgclienttaller.data.sources.remotes

import com.example.tfgclienttaller.data.sources.remotes.api.UsuariosService
import com.example.tfgclienttaller.domain.Usuario
import com.example.tfgclienttaller.utils.NetworkResult
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val usuariosService: UsuariosService) :
    BaseApiResponse() {

    suspend fun registerServer(usuario: Usuario) {
        safeApiCall(apicall = { usuariosService.saveUser(usuario) })
    }


    suspend fun doLogin(usuario: Usuario)  = safeApiCall(apicall = {
        usuariosService.doLogin(
            usuario.email,
            usuario.uidToken,
            usuario.tokenNotificacion
        )
    })
}

