package dev.hoanglong180903.baseproject.data.datasource.firestore

import dev.hoanglong180903.baseproject.core.model.BaseResponse

interface FirestoreDataSource {
    suspend fun <T : Any> getDocument(
        collectionName: String,
        uid: String,
        clazz: Class<T>
    ): BaseResponse<T>
}