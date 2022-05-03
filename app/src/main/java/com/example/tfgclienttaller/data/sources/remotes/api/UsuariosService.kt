package com.example.tfgclienttaller.data.sources.remotes.api

import com.example.tfgclienttaller.domain.ApiRespuesta
import com.example.tfgclienttaller.domain.Usuario
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface UsuariosService {

    @POST("usuarios")
    suspend fun saveUser(@Body usuario: Usuario): Response<ApiRespuesta>

    @POST("usuarios/token")
    fun sendToken(@Query("token") token: String): Response<ApiRespuesta>

    @GET("usuarios/doLogin")
    suspend fun doLogin(
        @Query("email") email: String,
        @Query("tokenSesion") tokenSesion: String,
        @Query("tokenNotificacion") tokenNotificacion: String
    ): Response<Usuario>
}