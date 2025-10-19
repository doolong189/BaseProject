package dev.hoanglong180903.baseproject.data.repositoryImpl

import com.google.firebase.firestore.FirebaseFirestore
import dev.hoanglong180903.baseproject.core.common.DataResult
import dev.hoanglong180903.baseproject.core.base.BaseRepository
import dev.hoanglong180903.baseproject.core.model.BaseResponse
import dev.hoanglong180903.baseproject.data.datasource.firestore.FirestoreDataSource
import dev.hoanglong180903.baseproject.domain.repository.firestore.FirestoreRepository
import kotlinx.coroutines.tasks.await

class FirestoreDataSourceImpl<T : Any>(
    private val datasource: FirestoreDataSource<T>,
) : BaseRepository(), FirestoreRepository<T> {
    override suspend fun add(item: T): DataResult<String> {
        return safeFirebaseCall { datasource.add(item) }
    }

    override suspend fun getAll(): DataResult<List<T>> {
        return safeFirebaseCall { datasource.getAll() }
    }

    override suspend fun getById(id: String): DataResult<T?> {
        return safeFirebaseCall { datasource.getById(id) }
    }

    override suspend fun update(id: String, data: Map<String, Any>): DataResult<Unit> {
        return safeFirebaseCall { datasource.update(id, data) }
    }

    override suspend fun delete(id: String): DataResult<Unit> {
        return safeFirebaseCall { datasource.delete(id) }
    }

    override suspend fun whereEqual(field: String, value: Any): DataResult<List<T>> {
        return safeFirebaseCall { datasource.whereEqual(field, value) }
    }


}