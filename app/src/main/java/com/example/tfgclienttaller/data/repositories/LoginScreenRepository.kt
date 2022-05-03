package com.example.tfgclienttaller.data.repositories

import androidx.activity.ComponentActivity
import com.example.tfgclienttaller.data.sources.firebase.Authentication
import com.example.tfgclienttaller.data.sources.remotes.RemoteDataSource
import com.example.tfgclienttaller.domain.Usuario
import com.google.android.gms.auth.api.identity.SignInCredential
import javax.inject.Inject

class LoginScreenRepository @Inject constructor(
    private val authentication: Authentication,
) {
    suspend fun doLoginFirebase(activity: ComponentActivity, usuario: Usuario) =
        authentication.login(activity, usuario)

    suspend fun doLoginWithGoogleFirebase(activity: ComponentActivity, credential: SignInCredential,tokenNotification:String) =
        authentication.loginWithGoogleFirebase(activity,credential,tokenNotification)

    fun doLoginWithGoogle(activity: ComponentActivity) = authentication.loginWithGoogle(activity)

}