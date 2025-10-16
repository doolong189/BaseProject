package dev.hoanglong180903.baseproject.domain.repository.firestore

import dev.hoanglong180903.baseproject.core.common.DataResult
import dev.hoanglong180903.baseproject.core.model.BaseResponse

interface FirestoreRepository {
    suspend fun <T : Any> getDocument(
        collectionName: String,
        uid: String,
        clazz: Class<T>
    ): DataResult<BaseResponse<T>>
}