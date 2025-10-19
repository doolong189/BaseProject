package dev.hoanglong180903.baseproject.data.datasource.firestore

import dev.hoanglong180903.baseproject.core.model.BaseResponse

interface FirestoreDataSource<T> {
    suspend fun add(item: T): String
    suspend fun getAll(): List<T>
    suspend fun getById(id: String): T?
    suspend fun update(id: String, data: Map<String, Any>)
    suspend fun delete(id: String)
    suspend fun whereEqual(field: String, value: Any): List<T>
}