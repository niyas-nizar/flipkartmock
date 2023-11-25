package com.niyas.flipkartmock.data

import timber.log.Timber
import java.io.IOException


suspend fun <T : Any> safeApiCall(
    call: suspend () -> NetworkResult<T>,
    errorMessage: String
): NetworkResult<T> {
    return try {
        call()
    } catch (e: Exception) {
        e.printStackTrace()
        Timber.d("Error>>>>> $e")
        Timber.d("Error>>>>> ${e.message}")
        if (e.message?.contains("No address associated with hostname") == true) {
            NetworkResult.Failure.Exception(
                IOException("Please check your internet connectivity", e)
            )
        } else {
            NetworkResult.Failure.Exception(IOException(errorMessage, e))
        }
    }
}