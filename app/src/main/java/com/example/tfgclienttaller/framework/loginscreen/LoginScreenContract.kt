package com.example.tfgclienttaller.framework.loginscreen

import android.content.Context
import androidx.activity.ComponentActivity

interface LoginScreenContract {

    sealed class Event{
        data class onEmailChange(val email: String) : Event()
        data class onPassChange(val pass: String) : Event()
        data class doLogin(val activity: ComponentActivity) : Event()
        data class register (val activity: ComponentActivity): Event()
    }
}