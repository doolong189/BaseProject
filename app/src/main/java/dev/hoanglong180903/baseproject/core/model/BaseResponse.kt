package dev.hoanglong180903.baseproject.core.model

data class BaseResponse<T : Any>(val code: Int, val message: String, val data: T? = null)