package dev.hoanglong180903.baseproject.data.repositoryImpl

import com.google.firebase.firestore.FirebaseFirestore
import dev.hoanglong180903.baseproject.core.common.DataResult
import dev.hoanglong180903.baseproject.core.base.BaseRepository
import dev.hoanglong180903.baseproject.core.model.BaseResponse
import dev.hoanglong180903.baseproject.data.datasource.firestore.FirestoreDataSource
import dev.hoanglong180903.baseproject.domain.repository.firestore.FirestoreRepository

class FirestoreRepositoryImpl(private val firebaseRepository: FirestoreDataSource) : FirestoreRepository, BaseRepository(){
    override suspend fun <T : Any> getDocument(
        collectionName: String,
        uid: String,
        clazz: Class<T>
    ): DataResult<BaseResponse<T>> {
        return safeFirebaseCall { firebaseRepository.getDocument(collectionName, uid, clazz) }
    }

}