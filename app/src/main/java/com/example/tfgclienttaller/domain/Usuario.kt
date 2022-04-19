package com.example.tfgclienttaller.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Usuario(
    val idUsuario: Int = 0,
    val email: String,
    val nombre: String,
    val pass: String,
    val apellido: String,
    val telefono: String,
    val idtipoUsuario: Int = -1,
    val tokenNotificacion: String = "",
    val uidToken: String = "",
) : Parcelable
