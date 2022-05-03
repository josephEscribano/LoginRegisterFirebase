package com.example.tfgclienttaller.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Usuario(
    val idUsuario: Int = 0,
    val email: String = "",
    val nombre: String = "",
    var pass: String = "",
    val apellido: String = "",
    val telefono: String = "",
    val tipoUsuarioByIdTipousuarioFkUsuario: TipoUsuario? = null,
    val tokenNotificacion: String = "",
    var uidToken: String = "",
) : Parcelable
