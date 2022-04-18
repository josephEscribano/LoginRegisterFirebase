package com.example.tfgclienttaller.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Usuario(
    val idUsuario: Int = 0,
    val email: String,
    val name: String,
    val pass: String,
    val surname: String,
    val phone: String,
    val tipoUsuario: Int = -1,
    val tokenNotificacion: String = "",
    val uidToken: String = "",
) : Parcelable
