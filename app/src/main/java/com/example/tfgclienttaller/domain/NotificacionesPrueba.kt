package com.example.tfgclienttaller.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime

@Parcelize
data class NotificacionesPrueba(
    val id:Int,
    val nombre:String,
    val fecha: LocalDateTime
) : Parcelable
