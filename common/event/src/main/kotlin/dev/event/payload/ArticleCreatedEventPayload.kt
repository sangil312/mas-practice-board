package dev.event.payload

import dev.event.EventPayload
import java.time.LocalDateTime

data class ArticleCreatedEventPayload(
    val articleId: Long,
    val title: String,
    val content: String,
    val boardId: Long,
    val userId: Long,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
    val boardArticleCount: Long
) : EventPayload
