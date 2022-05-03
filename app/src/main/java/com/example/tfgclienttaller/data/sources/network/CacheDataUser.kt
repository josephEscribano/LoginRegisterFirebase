package com.example.tfgclienttaller.data.sources.network

import dagger.Provides
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Singleton



data class CacheDataUser(
    val email: String = "",
    var token: String = ""
)
