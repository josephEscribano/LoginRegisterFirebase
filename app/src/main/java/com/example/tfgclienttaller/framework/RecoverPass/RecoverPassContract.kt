package com.example.tfgclienttaller.framework.RecoverPass

import androidx.activity.ComponentActivity
import com.example.tfgclienttaller.framework.loginscreen.LoginScreenContract

interface RecoverPassContract {

    sealed class Event{
        data class onEmailChange(val email: String) : Event()
        data class recoverPass(val activity: ComponentActivity) : Event()
    }
}