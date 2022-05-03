package com.example.tfgclienttaller.data.sources.network

import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.util.*
import javax.inject.Inject

class AuthorizationInterceptor @Inject constructor(
    private val cache :CacheDataUser
): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original: Request = chain.request()
        val request: Request
        request = original.newBuilder().header(ConstantesNetwork.AUTHORIZATION, ConstantesNetwork.BEARER + cache.token).build();

        val response = chain.proceed(request)

        return response
    }
}