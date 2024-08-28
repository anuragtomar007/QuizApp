package com.anurag.quizappcompose.common

sealed class Resource<T>(val message: String? = null, val data: T? = null) {

    class Success<T>(data: T?) : Resource<T>(data = data)
    class Error<T>(message: String?) : Resource<T>(message)
    class Loading<T> : Resource<T>()
}