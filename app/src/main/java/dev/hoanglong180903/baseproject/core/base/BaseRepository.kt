package dev.hoanglong180903.baseproject.core.base

import com.google.gson.Gson
import dev.hoanglong180903.baseproject.core.common.DataResult
import dev.hoanglong180903.baseproject.core.extension.traceErrorException
import dev.hoanglong180903.baseproject.core.model.ErrorResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

open class BaseRepository {
    suspend fun <T> safeApiCall(request: suspend () -> Response<T>): DataResult<T> {
        return try {
            val response = request.invoke()
            if (response.isSuccessful) {
                response.body()?.let {
                    DataResult.Success(it)
                } ?: DataResult.Error(response.errorBody().toString())
            } else {
                val errorBody = response.errorBody()?.string()
                val error = Gson().fromJson(errorBody, ErrorResponse::class.java)
                DataResult.Error(error.message)
            }
        } catch (e: Throwable) {
            DataResult.Error(traceErrorException(e).getErrorMessage())
        }
    }

    suspend fun <T> safeFirebaseCall(
        dispatcher: CoroutineDispatcher = Dispatchers.IO,
        request: suspend CoroutineScope.() -> T
    ): DataResult<T> {
        return withContext(dispatcher) {
            try {
                DataResult.Success(request())
            } catch (e: Exception) {
                DataResult.Error(e.toString())
            }
        }
    }
}