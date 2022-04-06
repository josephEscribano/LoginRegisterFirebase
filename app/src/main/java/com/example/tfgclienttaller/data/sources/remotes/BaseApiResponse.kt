package com.example.tfgclienttaller.data.sources.remotes

import com.example.tfgclienttaller.utils.Constantes
import com.example.tfgclienttaller.utils.NetworkResult
import retrofit2.Response

abstract class BaseApiResponse {

    suspend fun <T, R> safeApiCall(
        apicall: suspend () -> Response<R>,
        transform: (R) -> T
    ): NetworkResult<T> {
        try {
            val response = apicall()

            if (response.isSuccessful) {
                val body = response.body()
                body?.let {
                    return NetworkResult.Succcess(transform(body))
                }
            }
            return error("${response.code()} ${response.message()}")

        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(errorMessage: String): NetworkResult<T> =
        NetworkResult.Error(Constantes.MENSAJE_ERROR + errorMessage)
}