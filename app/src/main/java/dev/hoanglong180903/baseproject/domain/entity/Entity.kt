package dev.hoanglong180903.baseproject.domain.entity

interface Entity<T : Any> {
    fun toDomain(): T
    fun fromDomain(item: T): Entity<T>
}