package com.example.tfgclienttaller.framework.RecoverPass

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tfgclienttaller.data.sources.firebase.Authentication
import com.example.tfgclienttaller.framework.loginscreen.ConstantesLogin
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class RecoverPassScreenViewModel @Inject constructor(
    private val authentication: Authentication
) : ViewModel(){

    var email by mutableStateOf("")
        private set

    @SuppressLint("LongLogTag")
    fun handleEvent(event: RecoverPassContract.Event){
        when(event){
            is RecoverPassContract.Event.onEmailChange ->{
                email = event.email
            }
            is RecoverPassContract.Event.recoverPass -> {
                viewModelScope.launch {
                    try {
                        authentication.recuperarContraseña(event.activity,email)
                    }catch (e: Exception){
                        e.message?.let { Log.e("Error recuperar contraseña", it) }
                    }
                }
            }
        }
    }
}