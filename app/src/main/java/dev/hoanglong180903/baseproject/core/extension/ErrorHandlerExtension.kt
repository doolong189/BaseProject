package dev.hoanglong180903.baseproject.core.extension

import dev.hoanglong180903.baseproject.core.model.ErrorResponse
import dev.hoanglong180903.baseproject.core.model.UNKNOWN_ERROR_MESSAGE
import retrofit2.HttpException
import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

fun traceErrorException(throwable: Throwable?): ErrorResponse {
    return when (throwable) {
        is HttpException -> {
            when (throwable.code()) {
                400 -> ErrorResponse(
                    throwable.message,
                    throwable.code(),
                    ErrorResponse.ErrorStatus.BAD_REQUEST
                )
                401 -> ErrorResponse(
                    throwable.message(),
                    throwable.code(),
                    ErrorResponse.ErrorStatus.UNAUTHORIZED
                )
                403 -> ErrorResponse(
                    throwable.message(),
                    throwable.code(),
                    ErrorResponse.ErrorStatus.FORBIDDEN
                )
                404 -> ErrorResponse(
                    throwable.message(),
                    throwable.code(),
                    ErrorResponse.ErrorStatus.NOT_FOUND
                )
                405 -> ErrorResponse(
                    throwable.message(),
                    throwable.code(),
                    ErrorResponse.ErrorStatus.METHOD_NOT_ALLOWED
                )
                409 -> ErrorResponse(
                    throwable.message(),
                    throwable.code(),
                    ErrorResponse.ErrorStatus.CONFLICT
                )
                500 -> ErrorResponse(
                    throwable.message(),
                    throwable.code(),
                    ErrorResponse.ErrorStatus.INTERNAL_SERVER_ERROR
                )
                else -> ErrorResponse(
                    UNKNOWN_ERROR_MESSAGE,
                    0,
                    ErrorResponse.ErrorStatus.UNKNOWN_ERROR
                )
            }
        }

        is SocketTimeoutException -> ErrorResponse(throwable.message, ErrorResponse.ErrorStatus.TIMEOUT)

        is IOException -> ErrorResponse(throwable.message, ErrorResponse.ErrorStatus.NO_CONNECTION)

        is ConnectException -> ErrorResponse(throwable.message, ErrorResponse.ErrorStatus.NO_CONNECTION)

        is UnknownHostException ->  ErrorResponse(throwable.message, ErrorResponse.ErrorStatus.NO_CONNECTION)

        else -> ErrorResponse(UNKNOWN_ERROR_MESSAGE, 0, ErrorResponse.ErrorStatus.UNKNOWN_ERROR)
    }
}