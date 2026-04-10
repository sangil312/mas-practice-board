package dev.article.domain

import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.MappedSuperclass
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

@MappedSuperclass
abstract class BaseEntity {
    @CreationTimestamp
    val createdAt: LocalDateTime = LocalDateTime.MIN

    @UpdateTimestamp
    val updatedAt: LocalDateTime = LocalDateTime.MIN

    @Enumerated(EnumType.STRING)
    private var state = EntityState.ACTIVE

    fun active() {
        state = EntityState.ACTIVE
    }

    fun delete(){
        state = EntityState.DELETED
    }
}