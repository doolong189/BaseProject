package dev.hoanglong180903.baseproject.core.common
sealed class DataResult<out T> {
    data class Success<T>(val data: T) : DataResult<T>()

    data class Error(val message: String?) : DataResult<Nothing>()

    object Loading : DataResult<Nothing>()
}