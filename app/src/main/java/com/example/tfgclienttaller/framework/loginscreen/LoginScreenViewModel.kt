package com.example.tfgclienttaller.framework.loginscreen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tfgclienttaller.data.repositories.LoginScreenRepository
import com.example.tfgclienttaller.data.sources.firebase.MyFirebaseMessagingService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val loginScreenRepository: LoginScreenRepository,
    @SuppressLint("StaticFieldLeak") private val myFirebaseMessagingService: MyFirebaseMessagingService
    ): ViewModel(){

    var email by mutableStateOf("")
        private set

    var pass by mutableStateOf("")
        private set


    fun handleEvent(event: LoginScreenContract.Event){
        when(event){

            //TODO MEJORAR LA INTERFAZ QUE ES UNA MIERDA
            //TODO Añadir un snackbar para ver si se ha logueado o no
            //TODO Enviar el usuario junto con el token a mi servidor para registrar al usuario y loguearlo en el server
            is LoginScreenContract.Event.doLogin -> {
                viewModelScope.launch {
                    try {
                        loginScreenRepository.doLogin(event.activity,email,pass)
                    }catch (e:Exception){
                        e.message?.let { Log.e("Error Login", it) }
                    }
                }
            }
            is LoginScreenContract.Event.onPassChange -> {
                pass = event.pass
            }
            is LoginScreenContract.Event.onEmailChange -> {
                email = event.email
            }
            is LoginScreenContract.Event.sendToken -> {
                val token = myFirebaseMessagingService.sendToken()
                loginScreenRepository.sendToken(token)
            }
        }
    }

}