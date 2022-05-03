package com.example.tfgclienttaller.data.model

import com.example.tfgclienttaller.domain.MyToken

fun TokenEntity.toMyToken() : MyToken {
    return MyToken(token)
}

fun MyToken.toTokenEntity() : TokenEntity{
    return TokenEntity(0,token)
}