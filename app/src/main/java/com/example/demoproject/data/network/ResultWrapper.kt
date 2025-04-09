package com.example.demoproject.data.network

sealed class ResultWrapper<out T> {
    data class Success<T>(val data: T): ResultWrapper<T>()
    data class Error(val message: String, val code: Int? = null): ResultWrapper<Nothing>()
}

inline fun <T> ResultWrapper<T>.success(block: (T) -> Unit): ResultWrapper<T> {
    if (this is ResultWrapper.Success) block(data)
    return this
}

inline fun <T> ResultWrapper<T>.error(block: (ResultWrapper.Error) -> Unit): ResultWrapper<T> {
    if (this is ResultWrapper.Error) block(this)
    return this
}
