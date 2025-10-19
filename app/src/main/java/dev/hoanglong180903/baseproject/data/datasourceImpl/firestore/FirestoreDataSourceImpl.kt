package dev.hoanglong180903.baseproject.data.datasourceImpl.firestore

import com.google.firebase.firestore.FirebaseFirestore
import dev.hoanglong180903.baseproject.data.datasource.firestore.FirestoreDataSource
import kotlinx.coroutines.tasks.await

class FirestoreDataSourceImpl<T : Any>(
    private val collectionPath: String,
    private val clazz: Class<T>
) : FirestoreDataSource<T> {

    private val db = FirebaseFirestore.getInstance()
    private val collectionRef = db.collection(collectionPath)

    override suspend fun add(item: T): String {
        val docRef = collectionRef.add(item).await()
        return docRef.id
    }

    override suspend fun getAll(): List<T> {
        val snapshot = collectionRef.get().await()
        return snapshot.toObjects(clazz)
    }

    override suspend fun getById(id: String): T? {
        val doc = collectionRef.document(id).get().await()
        return if (doc.exists()) doc.toObject(clazz) else null
    }

    override suspend fun update(id: String, data: Map<String, Any>) {
        collectionRef.document(id).update(data).await()
    }

    override suspend fun delete(id: String) {
        collectionRef.document(id).delete().await()
    }

    override suspend fun whereEqual(field: String, value: Any): List<T> {
        val snapshot = collectionRef.whereEqualTo(field, value).get().await()
        return snapshot.toObjects(clazz)
    }
}