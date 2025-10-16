package dev.hoanglong180903.baseproject.data.datasourceImpl.firestore

import com.google.firebase.firestore.FirebaseFirestore
import dev.hoanglong180903.baseproject.core.model.BaseResponse
import dev.hoanglong180903.baseproject.data.datasource.firestore.FirestoreDataSource
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class FirestoreDatasourceImpl : FirestoreDataSource {
    override suspend fun <T : Any> getDocument(
        collectionName: String,
        uid: String,
        clazz: Class<T>
    ): BaseResponse<T> {
        return suspendCoroutine { continuation ->
            val db = FirebaseFirestore.getInstance()
            db.collection(collectionName)
                .document(uid)
                .get()
                .addOnSuccessListener { document ->
                    if (document.exists()) {
                        val data = document.toObject(clazz)
                        if (data != null) {
                            continuation.resume(BaseResponse(200, "Thành công", data))
                        } else {
                            continuation.resume(BaseResponse(500, "Lỗi dữ liệu", null))
                        }
                    } else {
                        continuation.resume(BaseResponse(404, "Không tìm thấy dữ liệu", null))
                    }
                }
                .addOnFailureListener { exception ->
                    continuation.resume(BaseResponse(500, exception.localizedMessage ?: "Lỗi không xác định", null))
                }
        }
    }
}