package com.example.tfgclienttaller.framework.registerScreen

import androidx.activity.ComponentActivity
import com.example.tfgclienttaller.domain.Usuario
import com.example.tfgclienttaller.framework.loginscreen.LoginScreenContract

interface RegisterScreenContract {

    sealed class Event {
        data class onEmailChange(val email: String) : Event()
        data class onPassChange(val pass: String) : Event()
        data class onSurnameChange(val surname: String) : Event()
        data class onPhoneChange(val phone: String) : Event()
        data class onNameChange(val name: String) : Event()
        data class register(val activity: ComponentActivity) : Event()
        object registerServer : Event()
    }
}