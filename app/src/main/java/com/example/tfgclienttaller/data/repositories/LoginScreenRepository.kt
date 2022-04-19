package com.example.tfgclienttaller.data.repositories

import android.content.Context
import androidx.activity.ComponentActivity
import com.example.tfgclienttaller.data.sources.firebase.Authentication
import com.example.tfgclienttaller.data.sources.remotes.RemoteDataSource
import javax.inject.Inject

class LoginScreenRepository @Inject constructor(
    private val authentication: Authentication,
    private val remoteDataSource: RemoteDataSource){
    fun doLogin(activity: ComponentActivity, email: String, pass: String) = authentication.login(activity,email, pass)
    fun sendToken(token:String) = remoteDataSource.sendToken(token)
}