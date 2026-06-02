package dev.outboxmessagerelay

import dev.event.EventType
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime

@Entity
@Table(name = "outbox")
class Outbox(
    @Id
    val id: Long,
    val shardKey: Long,
    @Enumerated(EnumType.STRING)
    val eventType: EventType,
    val payload: String?,
    @CreationTimestamp
    val createdAt: LocalDateTime = LocalDateTime.MIN
) {
}
