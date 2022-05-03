package com.example.tfgclienttaller.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TipoUsuario(
    val idTipoUsuario: Int,
    val tipo: String
) : Parcelable
