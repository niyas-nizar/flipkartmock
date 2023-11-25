package com.niyas.flipkartmock.data

import retrofit2.Response

sealed class NetworkResult<out T> {

    data class Success<T>(val response: Response<T>) : NetworkResult<T>() {
        val data: T? = response.body()
        override fun toString() = "[NetworkResult.Success](data=$data)"
    }

    sealed class Failure<T> {
        data class Error<T>(val response: Response<T>) : NetworkResult<T>() {
            private val statusCode = response.code()
            var errorData: ResponseClass? = null
            override fun toString(): String =
                "[NetworkResult.Failure.Error-$statusCode](errorResponse=$response)"
        }

        data class Exception<T>(val exception: Throwable) : NetworkResult<T>() {
            val message: String? = exception.localizedMessage
            override fun toString(): String = "[NetworkResult.Failure.Exception](message=$message)"
        }
    }
}
