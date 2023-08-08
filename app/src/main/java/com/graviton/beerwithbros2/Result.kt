package com.graviton.beerwithbros2

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.cancellable
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.retryWhen
import java.io.IOException
import java.lang.Exception

sealed interface Result<out T> {
    data class Success<T>(val data: T): Result<T>
    data class Error(val exception: Throwable? = null): Result<Nothing>
    object Loading: Result<Nothing>
}

fun <T> Flow<T>.asResult(): Flow<Result<T>> {
    return this
        .map<T, Result<T>> {
            Result.Success(it)
        }
        .onStart {
            emit(Result.Loading)
        }
        .retryWhen {cause, attempt ->
            if(cause is IOException && attempt < 3) {
                delay(500L)
                true
            } else {
                false
            }
        }
        .catch {
            emit(Result.Error(it))
        }
}