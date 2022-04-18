package com.example.tfgclienttaller.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ApiRespuesta(
    val messsge: String
) : Parcelable
