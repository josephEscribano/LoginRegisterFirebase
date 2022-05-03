package com.example.tfgclienttaller.framework.loginscreen

import android.content.Context
import androidx.activity.ComponentActivity
import com.example.tfgclienttaller.domain.Usuario
import com.google.android.gms.auth.api.identity.SignInCredential

interface LoginScreenContract {

    sealed class Event{
        data class onEmailChange(val email: String) : Event()
        data class onPassChange(val pass: String) : Event()
        data class doLoginFirebase(val activity: ComponentActivity) : Event()
        data class doLoginWithGoogleFirebase(val activity: ComponentActivity,val credential: SignInCredential) : Event()
        data class doLoginWithGoogle(val activity: ComponentActivity) : Event()
        data class onLogueadoChange(val logueado: Boolean) : Event()
    }

    data class StateToken(
        val logueado : Boolean = false
    )
}