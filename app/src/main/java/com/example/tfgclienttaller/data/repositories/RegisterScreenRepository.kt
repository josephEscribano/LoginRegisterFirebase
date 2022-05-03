package com.example.tfgclienttaller.data.repositories

import androidx.activity.ComponentActivity
import com.example.tfgclienttaller.data.sources.firebase.Authentication
import com.example.tfgclienttaller.domain.Usuario
import javax.inject.Inject

class RegisterScreenRepository @Inject constructor(
    private val authentication: Authentication
) {

    suspend fun register(activity: ComponentActivity, usuario: Usuario) {
        authentication.register(activity, usuario)
    }
}