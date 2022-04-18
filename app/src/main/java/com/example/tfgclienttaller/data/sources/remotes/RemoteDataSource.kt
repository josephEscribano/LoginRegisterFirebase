package com.example.tfgclienttaller.data.sources.remotes

import com.example.tfgclienttaller.data.sources.remotes.api.UsuariosService
import com.example.tfgclienttaller.domain.Usuario
import javax.inject.Inject

class RemoteDataSource@Inject constructor(private val usuariosService: UsuariosService) : BaseApiResponse() {

    suspend fun registerServer(usuario: Usuario) =
        safeApiCall(apicall = {usuariosService.saveUser(usuario)})

    fun sendToken(token: String) = safeNotCorrutineApiCall(apicall = {usuariosService.sendToken(token)})
}