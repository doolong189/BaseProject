package dev.hoanglong180903.baseproject.domain.repository.firestore

import dev.hoanglong180903.baseproject.core.common.DataResult
import dev.hoanglong180903.baseproject.core.model.BaseResponse

interface FirestoreRepository<T : Any> {
    suspend fun add(item: T) : DataResult<String>
    suspend fun getAll() : DataResult<List<T?>>
    suspend fun getById(id: String) : DataResult<T?>
    suspend fun update(id: String, data: Map<String, Any>) : DataResult<Unit>
    suspend fun delete(id: String) : DataResult<Unit>
    suspend fun whereEqual(field: String, value: Any) : DataResult<List<T?>>
}