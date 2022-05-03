package com.example.tfgclienttaller.data.sources.firebase

import android.content.IntentSender
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.material.SnackbarHostState
import androidx.core.app.ActivityCompat.startIntentSenderForResult
import com.example.tfgclienttaller.R
import com.example.tfgclienttaller.data.repositories.LoginAndRegisterServerRepository
import com.example.tfgclienttaller.data.sources.network.CacheDataUser
import com.example.tfgclienttaller.domain.TipoUsuario
import com.example.tfgclienttaller.domain.Usuario
import com.example.tfgclienttaller.framework.registerScreen.ConstantesRegistrarase
import com.example.tfgclienttaller.utils.NetworkResult
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.auth.api.identity.SignInCredential
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


class Authentication @Inject constructor(
    private val loginAndRegisterServerRepository: LoginAndRegisterServerRepository,
    private val cacheDataUser: CacheDataUser
) {

    companion object {
        var logueado = false
    }

    @OptIn(DelicateCoroutinesApi::class)
    suspend fun login(activity: ComponentActivity, usuario: Usuario) {
        val auth: FirebaseAuth = Firebase.auth
        auth.signInWithEmailAndPassword(usuario.email, usuario.pass)
            .addOnCompleteListener(activity) { task ->
                if (task.isSuccessful) {

                    // Sign in success, update UI with the signed-in user's information
                    Log.d(ConstantesFirebase.LOGINSUCCESTAG, ConstantesFirebase.LOGINSUCCESSMENSAJE)
                    auth.currentUser?.getIdToken(true)
                        ?.addOnCompleteListener { taskUser ->
                            if (task.isSuccessful) {
                                val token = taskUser.result.token
                                if (token != null) {
                                    usuario.uidToken = token
                                    cacheDataUser.token = token
                                    GlobalScope.launch {
                                        loginAndRegisterServerRepository.doLogin(usuario).catch {
                                            Log.w(
                                                ConstantesFirebase.TAGERRORLOGINSERVER,
                                                ConstantesFirebase.MENSAJEERRORLOGINSERVER,
                                                task.exception
                                            )
                                        }.collect { result ->
                                            if (result is NetworkResult.Succcess) {
                                                logueado = true
                                            }
                                        }
                                    }
                                }

                            } else {
                                Log.w(
                                    ConstantesFirebase.ERRORGETTOKENTAG,
                                    ConstantesFirebase.MENSAJETAGTOKEN,
                                    task.exception
                                )
                            }
                        }


                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(
                        ConstantesFirebase.ERRORLOGINGTAG,
                        ConstantesFirebase.MENSAJETAGLOGIN,
                        task.exception
                    )
                    Toast.makeText(
                        activity, ConstantesFirebase.ERRORLOGIN,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    @OptIn(DelicateCoroutinesApi::class)
    suspend fun register(activity: ComponentActivity, usuario: Usuario) {
        val auth: FirebaseAuth = Firebase.auth


        auth.createUserWithEmailAndPassword(usuario.email, usuario.pass)
            .addOnCompleteListener(activity) { task ->
                if (task.isSuccessful) {
                    Log.d(
                        ConstantesFirebase.REGISTROSUCCESSTAG,
                        ConstantesFirebase.REGISTROSUCCESMENSAJE
                    )
                    val user = auth.currentUser
                    if (user != null) {
                        usuario.uidToken = user.uid
                        GlobalScope.launch {
                            loginAndRegisterServerRepository.registerServer(usuario)
                        }
                    }


                } else {
                    if (task.exception is FirebaseAuthWeakPasswordException) {
                        Log.w(
                            ConstantesFirebase.REGISTROERRORCREDENTIALTAG,
                            ConstantesFirebase.ERRORCONTRASEÑAINVALIDA,
                            task.exception
                        )
                        Toast.makeText(
                            activity, ConstantesFirebase.ERRORMENSAJECONTRASEÑAINVALIDA,
                            Toast.LENGTH_SHORT
                        ).show()
                    } else if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        Log.w(
                            ConstantesFirebase.REGISTROERRORCREDENTIALTAG,
                            ConstantesFirebase.REGISTROERRORMENSAJETAG,
                            task.exception
                        )

                        Toast.makeText(
                            activity, ConstantesFirebase.ERRORCORREOINVALIDO,
                            Toast.LENGTH_SHORT
                        ).show()
                    } else if (task.exception is FirebaseAuthUserCollisionException) {
                        Log.w(
                            ConstantesFirebase.REGISTROERROREMAILREPETIDO,
                            ConstantesFirebase.REGISTROMENSAJEEMAILREPETIDO,
                            task.exception
                        )
                        Toast.makeText(
                            activity, ConstantesFirebase.ERRORMENSAJEEMAILREPETIDO,
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                }

            }

    }

    @OptIn(DelicateCoroutinesApi::class)
    fun loginWithGoogle(activity: ComponentActivity) {

        val oneTapClient: SignInClient = Identity.getSignInClient(activity)
        val signInRequest: BeginSignInRequest = BeginSignInRequest.builder()
            .setPasswordRequestOptions(
                BeginSignInRequest.PasswordRequestOptions.builder()
                    .setSupported(true)
                    .build()
            )
            .setGoogleIdTokenRequestOptions(
                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    // Your server's client ID, not your Android client ID.
                    .setServerClientId(activity.getString(R.string.server_id))
                    // Only show accounts previously used to sign in.
                    .setFilterByAuthorizedAccounts(false)
                    .build()
            )
            // Automatically sign in when exactly one credential is retrieved.
            .setAutoSelectEnabled(true)
            .build()

        oneTapClient.beginSignIn(signInRequest)
            .addOnSuccessListener(activity) { result ->
                try {
                    startIntentSenderForResult(
                        activity,
                        result.pendingIntent.intentSender, 1,
                        null, 0, 0, 0, null
                    )
                } catch (e: IntentSender.SendIntentException) {
                    Log.e(
                        ConstantesFirebase.ERRORINICIOSESIONGOOGLE,
                        ConstantesFirebase.MENSAJEINICIOSESIONGOOGLE + "${e.localizedMessage}"
                    )
                }
            }
            .addOnFailureListener(activity) { e ->
                // No saved credentials found. Launch the One Tap sign-up flow, or
                // do nothing and continue presenting the signed-out UI.
                Log.d(
                    ConstantesFirebase.ERRORINICIOSESIONGOOGLE,
                    e.localizedMessage ?: ConstantesFirebase.MENSAJEERRORONETAPGOOGLE
                )
            }
    }

    @OptIn(DelicateCoroutinesApi::class)
    suspend fun loginWithGoogleFirebase(
        activity: ComponentActivity,
        credential: SignInCredential,
        tokenNotificacion: String
    ) {
        val idToken = credential.googleIdToken
        val auth: FirebaseAuth = Firebase.auth
        when {
            idToken != null -> {
                // Got an ID token from Google. Use it to authenticate
                // with Firebase.
                val firebaseCredential = GoogleAuthProvider.getCredential(idToken, null)
                auth.signInWithCredential(firebaseCredential)
                    .addOnCompleteListener(activity) { task ->
                        if (task.isSuccessful) {

                            val user = auth.currentUser
                            var email = ""
                            var name = ""
                            var phone = ""
                            user?.let { itUser ->
                                for (profile in itUser.providerData) {
                                    profile.email?.let {
                                        email = it
                                    }
                                    profile.phoneNumber?.let {
                                        phone = it
                                    }
                                    profile.displayName?.let {
                                        name = it
                                    }

                                }
                            }
                            auth.currentUser?.getIdToken(true)
                                ?.addOnCompleteListener { taskUser ->
                                    if (task.isSuccessful) {
                                        val token = taskUser.result.token
                                        if(token != null){
                                            cacheDataUser.token = token
                                        }

                                        if (token != null) {
                                            GlobalScope.launch {
                                                loginAndRegisterServerRepository.registerServer(
                                                    Usuario(
                                                        email = email,
                                                        nombre = name,
                                                        telefono = phone,
                                                        tokenNotificacion = tokenNotificacion,
                                                        tipoUsuarioByIdTipousuarioFkUsuario = TipoUsuario(
                                                            2,
                                                            ConstantesRegistrarase.CLIENTE
                                                        )
                                                    )
                                                )
                                                loginAndRegisterServerRepository.doLogin(
                                                    Usuario(
                                                        email = email,
                                                        uidToken = token,
                                                        tokenNotificacion = tokenNotificacion
                                                    )
                                                )
                                            }
                                        }
//                                        Toast.makeText(
//                                            activity, "Logueado con google",
//                                            Toast.LENGTH_LONG
//                                        ).show()

                                    } else {
                                        Log.w(
                                            ConstantesFirebase.ERRORGETTOKENTAG,
                                            ConstantesFirebase.MENSAJETAGTOKEN,
                                            task.exception
                                        )
                                    }
                                }

                        } else {
                            Log.w(
                                ConstantesFirebase.ERRORGETTOKENTAG,
                                ConstantesFirebase.MENSAJETAGTOKEN,
                                task.exception
                            )
                        }
                    }
            }
            else -> {
                // Shouldn't happen.
                Log.d(
                    ConstantesFirebase.TAGCREDENTIALIDTOKEN,
                    ConstantesFirebase.MENSAJECREDENTIALIDTOKEN
                )
            }
        }
    }


    fun recuperarContraseña(activity: ComponentActivity,email:String){

        val auth = Firebase.auth
        auth.setLanguageCode(ConstantesFirebase.LANGUAGE)
        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(
                        activity, ConstantesFirebase.CORREOENVIADO,
                        Toast.LENGTH_SHORT
                    ).show()

                }else{
                    Toast.makeText(
                        activity, ConstantesFirebase.CORREONOENVIADO,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }


}