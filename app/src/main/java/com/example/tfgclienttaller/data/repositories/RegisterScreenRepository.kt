package com.example.tfgclienttaller.data.repositories

import androidx.activity.ComponentActivity
import com.example.tfgclienttaller.data.sources.firebase.Authentication
import com.example.tfgclienttaller.data.sources.remotes.RemoteDataSource
import com.example.tfgclienttaller.domain.Usuario
import javax.inject.Inject

class RegisterScreenRepository @Inject constructor(
    private val authentication: Authentication,
    private val remoteDataSource: RemoteDataSource
){
    fun register(activity: ComponentActivity, email: String, pass: String) = authentication.register(activity, email, pass)
    suspend fun registerServer(usuario: Usuario) {
        remoteDataSource.registerServer(usuario)
    }
}