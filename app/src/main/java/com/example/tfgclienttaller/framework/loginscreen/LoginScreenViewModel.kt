package com.example.tfgclienttaller.framework.loginscreen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tfgclienttaller.data.repositories.LocalRepository
import com.example.tfgclienttaller.data.repositories.LoginScreenRepository
import com.example.tfgclienttaller.data.sources.firebase.Authentication
import com.example.tfgclienttaller.data.sources.firebase.MyFirebaseMessagingService
import com.example.tfgclienttaller.domain.Usuario
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val loginScreenRepository: LoginScreenRepository,
    private val localRepository: LocalRepository,
    private val authentication: Authentication,
    @SuppressLint("StaticFieldLeak") private val myFirebaseMessagingService: MyFirebaseMessagingService
) : ViewModel() {

    private val _loginScreenState: MutableStateFlow<LoginScreenContract.StateToken> by lazy {
        MutableStateFlow(LoginScreenContract.StateToken())
    }
    val loginScreenState: StateFlow<LoginScreenContract.StateToken> = _loginScreenState


    private val _error = Channel<String>()
    val error = _error.receiveAsFlow()

    var email by mutableStateOf("")
        private set

    var pass by mutableStateOf("")
        private set





    @SuppressLint("LongLogTag")
    fun handleEvent(event: LoginScreenContract.Event) {
        when (event) {

            is LoginScreenContract.Event.doLoginFirebase -> {
                viewModelScope.launch {
                    try {
                        localRepository.getToken().catch(action = { cause ->
                            Log.e(
                                ConstantesLogin.TAGGETTOKEN,
                                cause.message ?: ConstantesLogin.ERROROBTENERTOKEN
                            )

                        }).collect { result ->
                            loginScreenRepository.doLoginFirebase(
                                event.activity, Usuario(
                                    email = email,
                                    pass = pass,
                                    tokenNotificacion = result
                                )
                            )
                            _loginScreenState.update { it.copy(logueado = true) }

                        }


                    } catch (e: Exception) {
                        e.message?.let {
                            Log.e(ConstantesLogin.ERRORLOGIN, it)
                            _error.send(e.message!!)
                        }

                    }
                }
            }
            is LoginScreenContract.Event.onPassChange -> {
                pass = event.pass
            }
            is LoginScreenContract.Event.onEmailChange -> {
                email = event.email
            }
            is LoginScreenContract.Event.doLoginWithGoogleFirebase -> {
                viewModelScope.launch {
                    try {
                        localRepository.getToken().catch(action = { cause ->
                            _error.send(cause.message ?: ConstantesLogin.ERROROBTENERTOKEN)

                        }).collect { result ->
                            loginScreenRepository.doLoginWithGoogleFirebase(
                                event.activity,
                                event.credential,
                                result
                            )
                            _loginScreenState.update { it.copy(logueado = true) }
                        }

                    } catch (e: Exception) {

                        e.message?.let { Log.e(ConstantesLogin.TAGERRORGOOGLEFIREBASE, it) }
                    }

                }
            }
            is LoginScreenContract.Event.doLoginWithGoogle -> {
                viewModelScope.launch {
                    try {
                        loginScreenRepository.doLoginWithGoogle(event.activity)

                    } catch (e: Exception) {
                        e.message?.let { Log.e(ConstantesLogin.TAGERRORLOGINGOOGLE, it) }
                    }
                }
            }
        }
    }

}