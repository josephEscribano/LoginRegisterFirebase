package com.example.tfgclienttaller.framework.registerScreen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tfgclienttaller.data.repositories.RegisterScreenRepository
import com.example.tfgclienttaller.data.sources.firebase.Authentication
import com.example.tfgclienttaller.domain.Usuario
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterScreenViewModel @Inject constructor(
    private val registerScreenRepository: RegisterScreenRepository,
    private val authentication: Authentication
    ) :
    ViewModel() {

    var email by mutableStateOf("")
        private set

    var pass by mutableStateOf("")
        private set

    var name by mutableStateOf("")
        private set

    var surname by mutableStateOf("")
        private set

    var phone by mutableStateOf("")
        private set

    fun handleEvent(event: RegisterScreenContract.Event) {
        when (event) {
            is RegisterScreenContract.Event.register -> {
                viewModelScope.launch {
                    try {
                        registerScreenRepository.register(event.activity, email, pass)
                    } catch (e: Exception) {
                        e.message?.let { Log.e("Error al registrar en firebase", it) }
                    }
                }
            }
            is RegisterScreenContract.Event.onEmailChange -> {
                email = event.email
            }
            is RegisterScreenContract.Event.onPassChange -> {
                pass = event.pass
            }
            is RegisterScreenContract.Event.onSurnameChange -> {
                surname = event.surname
            }
            is RegisterScreenContract.Event.onPhoneChange -> {
                phone = event.phone
            }
            is RegisterScreenContract.Event.registerServer -> {
                viewModelScope.launch {
                    try {
                        registerScreenRepository.registerServer(
                            Usuario(
                                email = email,
                                nombre = name,
                                apellido = surname,
                                pass = pass,
                                telefono = phone,
                                uidToken = authentication.dataUser(),)
                        )
                    }catch (e: Exception){
                        e.message?.let { Log.e("Error al registrar en el Server", it) }
                    }
                }
            }
            is RegisterScreenContract.Event.onNameChange -> {
                name = event.name
            }
        }
    }
}